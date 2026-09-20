package ru.mirea.kornilov.sway.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.usecases.GetProfileUseCase
import ru.mirea.kornilov.sway.domain.usecases.LoginUseCase
import ru.mirea.kornilov.sway.domain.usecases.RegisterUseCase

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val registerMode = MutableLiveData(false)
    private val statusRes = MutableLiveData<Int?>(null)
    private val loggedIn = MutableLiveData(false)

    init {
        Log.d(AuthViewModel::class.java.simpleName, "AuthViewModel created")
        if (GetProfileUseCase(authRepository).execute() != null) {
            loggedIn.value = true
        }
    }

    fun getRegisterMode(): LiveData<Boolean> = registerMode
    fun getStatusRes(): LiveData<Int?> = statusRes
    fun getLoggedIn(): LiveData<Boolean> = loggedIn

    fun toggleMode() {
        registerMode.value = !(registerMode.value ?: false)
        statusRes.value = null
    }

    fun submit(login: String, password: String, confirm: String) {
        statusRes.value = null
        val onResult: (Boolean) -> Unit = { ok ->
            if (ok) {
                loggedIn.postValue(true)
            } else {
                statusRes.postValue(R.string.auth_error)
            }
        }
        if (registerMode.value == true) {
            if (password != confirm) {
                statusRes.value = R.string.passwords_mismatch
                return
            }
            RegisterUseCase(authRepository).execute(login, password, onResult)
        } else {
            LoginUseCase(authRepository).execute(login, password, onResult)
        }
    }

    override fun onCleared() {
        Log.d(AuthViewModel::class.java.simpleName, "AuthViewModel cleared")
        super.onCleared()
    }
}
