package ru.mirea.kornilov.fragmentmanagerapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameView = view.findViewById<TextView>(R.id.textCountryName)
        val capitalView = view.findViewById<TextView>(R.id.textCapital)
        val descriptionView = view.findViewById<TextView>(R.id.textDescription)
        val viewModel = ViewModelProvider(requireActivity())[ShareViewModel::class.java]
        viewModel.getSelectedItem().observe(viewLifecycleOwner) { country ->
            Log.d(DetailsFragment::class.java.simpleName, country?.name ?: "empty")
            if (country == null) {
                nameView.setText(R.string.details_empty)
                capitalView.text = ""
                descriptionView.text = ""
            } else {
                nameView.text = country.name
                capitalView.text = getString(R.string.country_capital, country.capital)
                descriptionView.text = country.description
            }
        }
    }
}
