package ru.mirea.kornilov.fragmentmanagerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.list_container, CountriesFragment(), "header")
                .add(R.id.details_container, DetailsFragment(), "details")
                .commit()
        }
    }
}
