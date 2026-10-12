package ru.mirea.kornilov.sway.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType

class PlaceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = requireArguments()
        val name = args.getString(ARG_NAME).orEmpty()
        val country = args.getString(ARG_COUNTRY).orEmpty()
        val scene = sceneLabel(args.getString(ARG_SCENE))

        view.findViewById<TextView>(R.id.textPlaceTitle).text = name
        view.findViewById<TextView>(R.id.textPlaceMeta).text =
            getString(R.string.place_meta, country, getString(scene))
        view.findViewById<TextView>(R.id.textPlaceDescription).text =
            args.getString(ARG_DESCRIPTION).orEmpty()
        view.findViewById<ImageView>(R.id.imagePlace).apply {
            contentDescription = name
            load(args.getString(ARG_IMAGE)) {
                crossfade(true)
                placeholder(R.drawable.bg_place_placeholder)
                error(R.drawable.bg_place_placeholder)
            }
        }
        view.findViewById<View>(R.id.buttonBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity(), args.getInt(ARG_ID))
        )[PlaceViewModel::class.java]
        val weatherIcon = view.findViewById<ImageView>(R.id.imageWeather)
        val weatherTemp = view.findViewById<TextView>(R.id.textWeatherTemp)
        val weatherView = view.findViewById<TextView>(R.id.textWeather)
        val wantButton = view.findViewById<Button>(R.id.buttonWant)
        val visitedButton = view.findViewById<Button>(R.id.buttonVisited)
        val noteField = view.findViewById<EditText>(R.id.editNote)
        val saveButton = view.findViewById<TextView>(R.id.buttonSaveNote)
        val noteStatus = view.findViewById<TextView>(R.id.textNoteStatus)

        viewModel.getWeather().observe(viewLifecycleOwner) { weather ->
            val clear = weather.description.contains("ясно")
            weatherIcon.setImageResource(if (clear) R.drawable.ic_sun else R.drawable.ic_cloud)
            weatherIcon.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    if (clear) R.color.sway_sun else R.color.sway_muted
                )
            )
            weatherTemp.text = getString(R.string.place_weather_temp, weather.temperatureC)
            weatherView.text = weather.description
        }
        viewModel.getTrip().observe(viewLifecycleOwner) { trip ->
            wantButton.setText(
                if (trip?.wantToVisit == true) R.string.place_want_saved else R.string.place_want
            )
            visitedButton.setText(
                if (trip?.visited == true) R.string.place_visited_saved else R.string.place_visited
            )
            if (!noteField.hasFocus()) {
                noteField.setText(trip?.note.orEmpty())
            }
        }
        val noteLabel = view.findViewById<View>(R.id.textNoteLabel)
        val guestHint = view.findViewById<View>(R.id.textGuestHint)
        val mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity())
        )[MainViewModel::class.java]
        mainViewModel.getAuthorized().observe(viewLifecycleOwner) { authorized ->
            val userMode = if (authorized) View.VISIBLE else View.GONE
            wantButton.isEnabled = authorized
            visitedButton.isEnabled = authorized
            wantButton.alpha = if (authorized) 1f else 0.42f
            visitedButton.alpha = if (authorized) 1f else 0.42f
            noteLabel.visibility = userMode
            noteField.visibility = userMode
            saveButton.visibility = userMode
            if (!authorized) {
                noteStatus.visibility = View.GONE
            }
            guestHint.visibility = if (authorized) View.GONE else View.VISIBLE
        }
        wantButton.setOnClickListener { viewModel.saveWantToVisit() }
        visitedButton.setOnClickListener { viewModel.markVisited() }
        noteField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                noteStatus.visibility = View.GONE
            }
            override fun afterTextChanged(s: Editable?) = Unit
        })
        noteField.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                saveButton.post {
                    saveButton.requestRectangleOnScreen(
                        android.graphics.Rect(0, 0, saveButton.width, saveButton.height),
                        true
                    )
                }
            }
        }
        saveButton.setOnClickListener {
            noteField.clearFocus()
            hideKeyboard(noteField)
            viewModel.saveNote(noteField.text.toString())
            noteStatus.setText(R.string.place_note_saved)
            noteStatus.visibility = View.VISIBLE
        }
    }

    private fun hideKeyboard(view: View) {
        val manager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun sceneLabel(name: String?): Int = when (name) {
        SceneType.MOUNTAINS.name -> R.string.scene_mountains
        SceneType.SEA.name -> R.string.scene_sea
        SceneType.FOREST.name -> R.string.scene_forest
        else -> R.string.scene_city
    }

    companion object {
        fun open(manager: FragmentManager, place: Place) {
            val bundle = Bundle()
            bundle.putInt(ARG_ID, place.id)
            bundle.putString(ARG_NAME, place.name)
            bundle.putString(ARG_COUNTRY, place.country)
            bundle.putString(ARG_DESCRIPTION, place.description)
            bundle.putString(ARG_IMAGE, place.imageUrl)
            bundle.putString(ARG_SCENE, place.sceneType.name)
            manager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, PlaceFragment::class.java, bundle)
                .addToBackStack(null)
                .commit()
        }

        const val ARG_ID = "place_id"
        const val ARG_NAME = "place_name"
        const val ARG_COUNTRY = "place_country"
        const val ARG_DESCRIPTION = "place_description"
        const val ARG_IMAGE = "place_image"
        const val ARG_SCENE = "place_scene"
    }
}
