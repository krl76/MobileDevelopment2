package ru.mirea.kornilov.fragmentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val bundle = Bundle()
            bundle.putInt(BlankFragment.ARG_STUDENT_NUMBER, STUDENT_LIST_NUMBER)
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, BlankFragment::class.java, bundle)
                .commit()
        }
    }

    companion object {
        private const val STUDENT_LIST_NUMBER = 13
    }
}
