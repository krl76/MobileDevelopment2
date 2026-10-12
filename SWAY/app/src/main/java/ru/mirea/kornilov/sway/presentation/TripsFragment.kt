package ru.mirea.kornilov.sway.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.Trip

class TripsFragment : Fragment() {

    private lateinit var tripsViewModel: TripsViewModel
    private var places: List<Place> = emptyList()
    private var trips: List<Trip> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_trips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity())
        )[MainViewModel::class.java]
        tripsViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity())
        )[TripsViewModel::class.java]

        mainViewModel.getPlaces().observe(viewLifecycleOwner) { loaded ->
            places = loaded
            render(view)
        }
        tripsViewModel.getTrips().observe(viewLifecycleOwner) { loaded ->
            trips = loaded
            render(view)
        }
    }

    override fun onStart() {
        super.onStart()
        if (this::tripsViewModel.isInitialized) {
            tripsViewModel.reload()
        }
    }

    private fun render(view: View) {
        val placesById = places.associateBy { it.id }
        fill(
            view.findViewById(R.id.listWant),
            view.findViewById(R.id.emptyWant),
            section(placesById) { it.wantToVisit }
        )
        fill(
            view.findViewById(R.id.listVisited),
            view.findViewById(R.id.emptyVisited),
            section(placesById) { it.visited }
        )
    }

    private fun section(
        placesById: Map<Int, Place>,
        include: (Trip) -> Boolean
    ): List<Place> {
        return trips.filter(include)
            .mapNotNull { placesById[it.placeId] }
            .sortedBy { it.name }
    }

    private fun fill(container: LinearLayout, empty: View, rows: List<Place>) {
        container.removeAllViews()
        val showEmpty = rows.isEmpty()
        empty.visibility = if (showEmpty) View.VISIBLE else View.GONE
        container.visibility = if (showEmpty) View.GONE else View.VISIBLE
        if (showEmpty) {
            return
        }
        val inflater = LayoutInflater.from(container.context)
        rows.forEach { place ->
            val row = inflater.inflate(R.layout.item_trip, container, false)
            row.findViewById<TextView>(R.id.textTripName).text = place.name
            row.findViewById<TextView>(R.id.textTripCountry).text = place.country
            row.findViewById<ImageView>(R.id.imageTrip).load(place.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.bg_place_placeholder)
                error(R.drawable.bg_place_placeholder)
            }
            row.setOnClickListener { PlaceFragment.open(parentFragmentManager, place) }
            container.addView(row)
        }
    }
}
