package ru.mirea.kornilov.sway.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import ru.mirea.kornilov.sway.data.storage.ClientStorage
import ru.mirea.kornilov.sway.domain.models.AuthError
import ru.mirea.kornilov.sway.domain.models.AuthFailure
import ru.mirea.kornilov.sway.domain.models.User
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val clientStorage: ClientStorage
) : AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun login(login: String, password: String, onResult: (AuthFailure?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    readProfile { onResult(null) }
                } else {
                    onResult(authFailure(task.exception))
                }
            }
    }

    override fun register(
        login: String,
        password: String,
        nickname: String,
        firstName: String,
        lastName: String,
        onResult: (AuthFailure?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                val current = firebaseAuth.currentUser
                if (!task.isSuccessful || current == null) {
                    onResult(authFailure(task.exception))
                    return@addOnCompleteListener
                }
                val email = login.trim()
                val profile = hashMapOf(
                    "email" to email,
                    "nickname" to nickname.trim(),
                    "firstName" to firstName.trim(),
                    "lastName" to lastName.trim()
                )
                firestore.collection(USERS).document(current.uid).set(profile)
                    .addOnSuccessListener {
                        cacheProfile(email, nickname.trim(), firstName.trim(), lastName.trim())
                        onResult(null)
                    }
                    .addOnFailureListener { error ->
                        Log.e(TAG, "profile write failed", error)
                        firebaseAuth.signOut()
                        onResult(AuthFailure(AuthError.PROFILE_NOT_SAVED, error.message.orEmpty()))
                    }
            }
    }

    override fun updateProfile(
        nickname: String,
        firstName: String,
        lastName: String,
        onResult: (AuthFailure?) -> Unit
    ) {
        val current = firebaseAuth.currentUser
        if (current == null) {
            onResult(AuthFailure(AuthError.INVALID_CREDENTIALS))
            return
        }
        val email = current.email ?: clientStorage.getLogin().orEmpty()
        val profile = hashMapOf(
            "email" to email,
            "nickname" to nickname,
            "firstName" to firstName,
            "lastName" to lastName
        )
        firestore.collection(USERS).document(current.uid).set(profile)
            .addOnSuccessListener {
                if (email.isNotEmpty()) {
                    cacheProfile(email, nickname, firstName, lastName)
                }
                onResult(null)
            }
            .addOnFailureListener { error ->
                Log.e(TAG, "profile update failed", error)
                onResult(AuthFailure(AuthError.PROFILE_NOT_SAVED, error.message.orEmpty()))
            }
    }

    override fun logout() {
        firebaseAuth.signOut()
        clientStorage.clear()
    }

    override fun getCurrentUser(): User? {
        val email = firebaseAuth.currentUser?.email ?: clientStorage.getLogin()
        return email?.let { cachedUser(it) }
    }

    override fun loadProfile(onResult: (User?) -> Unit) {
        if (firebaseAuth.currentUser == null) {
            onResult(null)
            return
        }
        readProfile { onResult(getCurrentUser()) }
    }

    private fun readProfile(onDone: (Boolean) -> Unit) {
        val current = firebaseAuth.currentUser
        if (current == null) {
            onDone(false)
            return
        }
        firestore.collection(USERS).document(current.uid).get()
            .addOnSuccessListener { snapshot ->
                val email = snapshot.getString("email")
                    ?: current.email
                    ?: clientStorage.getLogin().orEmpty()
                if (email.isNotEmpty()) {
                    cacheProfile(
                        email,
                        snapshot.getString("nickname").orEmpty(),
                        snapshot.getString("firstName").orEmpty(),
                        snapshot.getString("lastName").orEmpty()
                    )
                }
                onDone(true)
            }
            .addOnFailureListener { error ->
                Log.e(TAG, "profile read failed", error)
                onDone(false)
            }
    }

    private fun cacheProfile(email: String, nickname: String, firstName: String, lastName: String) {
        clientStorage.saveLogin(email)
        clientStorage.saveProfile(email, nickname, firstName, lastName)
    }

    private fun authFailure(error: Exception?): AuthFailure {
        val code = (error as? FirebaseAuthException)?.errorCode
        val kind = when (code) {
            "ERROR_EMAIL_ALREADY_IN_USE" -> AuthError.EMAIL_ALREADY_USED
            "ERROR_WEAK_PASSWORD" -> AuthError.WEAK_PASSWORD
            "ERROR_NETWORK_REQUEST_FAILED" -> AuthError.NETWORK
            else -> AuthError.INVALID_CREDENTIALS
        }
        return AuthFailure(kind)
    }

    private fun cachedUser(email: String): User {
        return User(
            login = email,
            nickname = clientStorage.getNickname(email),
            firstName = clientStorage.getFirstName(email),
            lastName = clientStorage.getLastName(email)
        )
    }

    private companion object {
        const val TAG = "AuthRepository"
        const val USERS = "users"
    }
}
