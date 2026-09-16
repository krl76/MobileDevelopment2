package ru.mirea.kornilov.sway.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.data.repository.AuthRepositoryImpl
import ru.mirea.kornilov.sway.data.storage.sharedprefs.SharedPrefClientStorage
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.usecases.LoginUseCase
import ru.mirea.kornilov.sway.domain.usecases.RegisterUseCase

class AuthActivity : AppCompatActivity() {
    private var registerMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val clientStorage = SharedPrefClientStorage(this)
        val authRepository: AuthRepository = AuthRepositoryImpl(clientStorage)
        val loginUseCase = LoginUseCase(authRepository)
        val registerUseCase = RegisterUseCase(authRepository)

        if (authRepository.getCurrentUser() != null) {
            openMain()
            return
        }

        val loginField = findViewById<EditText>(R.id.editLogin)
        val passwordField = findViewById<EditText>(R.id.editPassword)
        val confirmField = findViewById<EditText>(R.id.editConfirmPassword)
        val confirmBlock = findViewById<LinearLayout>(R.id.confirmPasswordBlock)
        val submit = findViewById<Button>(R.id.buttonSubmit)
        val switchMode = findViewById<TextView>(R.id.textSwitchMode)
        val status = findViewById<TextView>(R.id.textAuthStatus)

        switchMode.setOnClickListener {
            registerMode = !registerMode
            confirmBlock.visibility = if (registerMode) View.VISIBLE else View.GONE
            submit.setText(if (registerMode) R.string.sign_up else R.string.sign_in)
            switchMode.setText(if (registerMode) R.string.to_login else R.string.to_register)
            status.text = ""
        }

        submit.setOnClickListener {
            val login = loginField.text.toString()
            val password = passwordField.text.toString()
            if (registerMode && password != confirmField.text.toString()) {
                status.setText(R.string.passwords_mismatch)
                return@setOnClickListener
            }
            val onResult: (Boolean) -> Unit = { ok ->
                if (ok) {
                    openMain()
                } else {
                    status.setText(R.string.auth_error)
                }
            }
            if (registerMode) {
                registerUseCase.execute(login, password, onResult)
            } else {
                loginUseCase.execute(login, password, onResult)
            }
        }
    }

    private fun openMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
