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
        val confirmBlock = findViewById<LinearLayout>(R.id.confirmPasswordBlock)
        val submit = findViewById<Button>(R.id.buttonSubmit)
        val switchMode = findViewById<TextView>(R.id.textSwitchMode)
        val status = findViewById<TextView>(R.id.textAuthStatus)

        vm.getLoggedIn().observe(this) { loggedIn ->
            if (loggedIn) openMain()
        }
        vm.getRegisterMode().observe(this) { registerMode ->
            confirmBlock.visibility = if (registerMode) View.VISIBLE else View.GONE
            submit.setText(if (registerMode) R.string.sign_up else R.string.sign_in)
            switchMode.setText(if (registerMode) R.string.to_login else R.string.to_register)
        }
        vm.getStatusRes().observe(this) { resId ->
            status.text = if (resId == null) "" else getString(resId)
        }

        switchMode.setOnClickListener { vm.toggleMode() }
        submit.setOnClickListener {
            vm.submit(
                loginField.text.toString(),
                passwordField.text.toString(),
                confirmField.text.toString()
            )
        }
    }

    private fun openMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
