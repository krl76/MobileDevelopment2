package ru.mirea.kornilov.fragmentmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CountriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[ShareViewModel::class.java]
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerCountries)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CountryAdapter(COUNTRIES) { country ->
            viewModel.selectItem(country)
        }
    }

    companion object {
        private val COUNTRIES = listOf(
            Country(
                "Франция",
                "Париж",
                "Страна в Западной Европе. Столица стоит на реке Сена."
            ),
            Country(
                "Япония",
                "Токио",
                "Островное государство в Восточной Азии."
            ),
            Country(
                "Италия",
                "Рим",
                "Средиземноморская страна. Столица стоит на реке Тибр."
            ),
            Country(
                "Норвегия",
                "Осло",
                "Скандинавская страна с длинной береговой линией и фьордами."
            ),
            Country(
                "Египет",
                "Каир",
                "Страна в Северной Африке. Столица стоит на реке Нил."
            ),
            Country(
                "Бразилия",
                "Бразилиа",
                "Крупнейшая страна Южной Америки."
            ),
            Country(
                "Канада",
                "Оттава",
                "Страна в Северной Америке. Занимает большую часть севера материка."
            ),
            Country(
                "Австралия",
                "Канберра",
                "Государство на одноимённом материке в Южном полушарии."
            )
        )
    }
}
