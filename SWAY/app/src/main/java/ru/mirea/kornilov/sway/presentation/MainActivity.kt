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

        val statusView = findViewById<TextView>(R.id.textCatalogStatus)
        vm.getPlaces().observe(this) { places ->
            adapter.setItems(places)
        }
        vm.getStatus().observe(this) { status ->
            when (status) {
                MainViewModel.CatalogStatus.LOADING -> {
                    statusView.visibility = android.view.View.VISIBLE
                    statusView.setText(R.string.catalog_loading)
                }
                MainViewModel.CatalogStatus.READY -> {
                    statusView.visibility = android.view.View.GONE
                }
                MainViewModel.CatalogStatus.NO_NETWORK -> {
                    statusView.visibility = android.view.View.VISIBLE
                    statusView.setText(R.string.catalog_no_network)
                }
                MainViewModel.CatalogStatus.BAD_RESPONSE -> {
                    statusView.visibility = android.view.View.VISIBLE
                    statusView.setText(R.string.catalog_bad_response)
                }
            }
        }
        vm.getAvatarLetter().observe(this) { letter ->
            findViewById<TextView>(R.id.textAvatar).text = letter
        }
    }
}
