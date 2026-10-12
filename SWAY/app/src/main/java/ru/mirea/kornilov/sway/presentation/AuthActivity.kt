package ru.mirea.kornilov.sway.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.sway.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(AuthActivity::class.java.simpleName, "AuthActivity created")

        val vm = ViewModelProvider(this, ViewModelFactory(this))[AuthViewModel::class.java]
        if (vm.getLoggedIn().value == true) {
            openMain()
            return
        }

        setContentView(R.layout.activity_auth)

        val loginField = findViewById<EditText>(R.id.editLogin)
        val passwordField = findViewById<EditText>(R.id.editPassword)
        val confirmField = findViewById<EditText>(R.id.editConfirmPassword)
        val nicknameField = findViewById<EditText>(R.id.editNickname)
        val firstNameField = findViewById<EditText>(R.id.editFirstName)
        val lastNameField = findViewById<EditText>(R.id.editLastName)
        val confirmBlock = findViewById<LinearLayout>(R.id.confirmPasswordBlock)
        val profileBlock = findViewById<LinearLayout>(R.id.profileBlock)
        val submit = findViewById<Button>(R.id.buttonSubmit)
        val switchMode = findViewById<TextView>(R.id.textSwitchMode)
        val status = findViewById<TextView>(R.id.textAuthStatus)

        vm.getLoggedIn().observe(this) { loggedIn ->
            if (loggedIn) openMain()
        }
        vm.getRegisterMode().observe(this) { registerMode ->
            confirmBlock.visibility = if (registerMode) View.VISIBLE else View.GONE
            profileBlock.visibility = if (registerMode) View.VISIBLE else View.GONE
            submit.setText(if (registerMode) R.string.sign_up else R.string.sign_in)
            switchMode.setText(if (registerMode) R.string.to_login else R.string.to_register)
        }
        vm.getStatus().observe(this) { message ->
            status.text = if (message == null) {
                ""
            } else if (message.detail.isBlank()) {
                getString(message.resId)
            } else {
                getString(message.resId) + "\n" + message.detail
            }
        }

        if (intent.getBooleanExtra(EXTRA_REGISTER, false)) {
            vm.openRegister()
        }
        switchMode.setOnClickListener { vm.toggleMode() }
        findViewById<View>(R.id.buttonGuest).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        submit.setOnClickListener {
            vm.submit(
                loginField.text.toString(),
                passwordField.text.toString(),
                confirmField.text.toString(),
                nicknameField.text.toString(),
                firstNameField.text.toString(),
                lastNameField.text.toString()
            )
        }
    }

    private fun openMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    companion object {
        const val EXTRA_REGISTER = "register"
    }
}
