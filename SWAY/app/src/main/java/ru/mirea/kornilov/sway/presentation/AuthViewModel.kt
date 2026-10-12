package ru.mirea.kornilov.sway.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.models.AuthError
import ru.mirea.kornilov.sway.domain.models.AuthFailure
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.usecases.GetProfileUseCase
import ru.mirea.kornilov.sway.domain.usecases.LoginUseCase
import ru.mirea.kornilov.sway.domain.usecases.RegisterUseCase

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val registerMode = MutableLiveData(false)
    private val status = MutableLiveData<AuthMessage?>(null)
    private val loggedIn = MutableLiveData(false)

    init {
        Log.d(AuthViewModel::class.java.simpleName, "AuthViewModel created")
        if (GetProfileUseCase(authRepository).execute() != null) {
            loggedIn.value = true
        }
    }

    fun getRegisterMode(): LiveData<Boolean> = registerMode
    fun getStatus(): LiveData<AuthMessage?> = status
    fun getLoggedIn(): LiveData<Boolean> = loggedIn

    fun toggleMode() {
        registerMode.value = !(registerMode.value ?: false)
        status.value = null
    }

    fun openRegister() {
        registerMode.value = true
        status.value = null
    }

    fun submit(
        login: String,
        password: String,
        confirm: String,
        nickname: String,
        firstName: String,
        lastName: String
    ) {
        status.value = null
        val onResult: (AuthFailure?) -> Unit = { failure ->
            if (failure == null) {
                loggedIn.postValue(true)
            } else {
                val detail = if (failure.error == AuthError.PROFILE_NOT_SAVED) failure.detail else ""
                status.postValue(AuthMessage(messageFor(failure.error), detail))
            }
        }
        if (registerMode.value == true) {
            if (nickname.isBlank() || firstName.isBlank() || lastName.isBlank()) {
                status.value = AuthMessage(R.string.profile_required)
                return
            }
            if (password != confirm) {
                status.value = AuthMessage(R.string.passwords_mismatch)
                return
            }
            RegisterUseCase(authRepository).execute(
                login,
                password,
                nickname,
                firstName,
                lastName,
                onResult
            )
        } else {
            LoginUseCase(authRepository).execute(login, password, onResult)
        }
    }

    private fun messageFor(error: AuthError): Int {
        return when (error) {
            AuthError.EMAIL_ALREADY_USED -> R.string.auth_email_used
            AuthError.WEAK_PASSWORD -> R.string.auth_weak_password
            AuthError.PROFILE_NOT_SAVED -> R.string.auth_profile_failed
            AuthError.NETWORK -> R.string.auth_network
            AuthError.INVALID_CREDENTIALS -> R.string.auth_error
        }
    }

    override fun onCleared() {
        Log.d(AuthViewModel::class.java.simpleName, "AuthViewModel cleared")
        super.onCleared()
    }
}

data class AuthMessage(
    val resId: Int,
    val detail: String = ""
)
