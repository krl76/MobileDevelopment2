package ru.mirea.kornilov.sway.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.sway.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(MainActivity::class.java.simpleName, "MainActivity created")

        val vm = ViewModelProvider(this, ViewModelFactory(this))[MainViewModel::class.java]
        val textView = findViewById<TextView>(R.id.textViewStub)

        vm.getSummary().observe(this) { value ->
            textView.text = value
        }

        findViewById<View>(R.id.buttonLoadStub).setOnClickListener {
            vm.loadStub()
        }
    }
}
