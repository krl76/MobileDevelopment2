package ru.mirea.kornilov.sway.presentation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.kornilov.sway.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(MainActivity::class.java.simpleName, "MainActivity created")

        val vm = ViewModelProvider(this, ViewModelFactory(this))[MainViewModel::class.java]
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPlaces)
        val adapter = PlaceAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        vm.getPlaces().observe(this) { places ->
            adapter.setItems(places)
        }
        vm.getAvatarLetter().observe(this) { letter ->
            findViewById<TextView>(R.id.textAvatar).text = letter
        }
    }
}
