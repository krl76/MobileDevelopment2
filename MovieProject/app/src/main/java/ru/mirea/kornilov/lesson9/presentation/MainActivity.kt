package ru.mirea.kornilov.lesson9.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.lesson9.R
import ru.mirea.kornilov.lesson9.domain.models.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(MainActivity::class.java.simpleName, "MainActivity created")

        val vm = ViewModelProvider(this, ViewModelFactory(this))[MainViewModel::class.java]

        val text = findViewById<EditText>(R.id.editTextMovie)
        val textView = findViewById<TextView>(R.id.textViewMovie)

        vm.getFavoriteMovie().observe(this) { value ->
            textView.text = value
        }

        findViewById<View>(R.id.buttonSaveMovie).setOnClickListener {
            vm.setText(Movie(2, text.text.toString()))
        }
        findViewById<View>(R.id.buttonGetMovie).setOnClickListener {
            vm.getText()
        }
    }
}
