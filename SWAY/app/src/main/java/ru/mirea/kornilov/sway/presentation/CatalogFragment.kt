package ru.mirea.kornilov.sway.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.kornilov.sway.R

class CatalogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity())
        )[MainViewModel::class.java]
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPlaces)
        val adapter = PlaceAdapter { place -> PlaceFragment.open(parentFragmentManager, place) }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val statusView = view.findViewById<TextView>(R.id.textCatalogStatus)
        viewModel.getPlaces().observe(viewLifecycleOwner) { places ->
            adapter.setItems(places)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) { status ->
            when (status) {
                MainViewModel.CatalogStatus.LOADING -> {
                    statusView.visibility = View.VISIBLE
                    statusView.setText(R.string.catalog_loading)
                }
                MainViewModel.CatalogStatus.READY -> {
                    statusView.visibility = View.GONE
                }
                MainViewModel.CatalogStatus.NO_NETWORK -> {
                    statusView.visibility = View.VISIBLE
                    statusView.setText(R.string.catalog_no_network)
                }
                MainViewModel.CatalogStatus.BAD_RESPONSE -> {
                    statusView.visibility = View.VISIBLE
                    statusView.setText(R.string.catalog_bad_response)
                }
            }
        }
    }
}
