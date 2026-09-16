package ru.mirea.kornilov.sway.data.repository

import com.google.firebase.auth.FirebaseAuth
import ru.mirea.kornilov.sway.data.storage.ClientStorage
import ru.mirea.kornilov.sway.domain.models.User
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val clientStorage: ClientStorage
) : AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun login(login: String, password: String, onResult: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    clientStorage.saveLogin(login)
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
    }

    override fun register(login: String, password: String, onResult: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    clientStorage.saveLogin(login)
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
    }

    override fun logout() {
        firebaseAuth.signOut()
        clientStorage.clear()
    }

    override fun getCurrentUser(): User? {
        val email = firebaseAuth.currentUser?.email ?: clientStorage.getLogin()
        return email?.let { User(it) }
    }
}
