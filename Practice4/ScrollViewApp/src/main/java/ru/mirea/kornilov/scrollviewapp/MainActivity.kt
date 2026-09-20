package ru.mirea.kornilov.scrollviewapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.BigInteger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wrapper = findViewById<LinearLayout>(R.id.wrapper)
        var term = BigInteger.ONE
        for (i in 1..100) {
            val view = layoutInflater.inflate(R.layout.item, wrapper, false)
            val text = view.findViewById<TextView>(R.id.textView)
            text.text = String.format("%d. %s", i, term.toString())
            wrapper.addView(view)
            term = term.multiply(BigInteger.TWO)
        }
    }
}
