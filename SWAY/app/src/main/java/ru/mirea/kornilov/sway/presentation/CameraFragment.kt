package ru.mirea.kornilov.sway.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType
import java.io.File

class CameraFragment : Fragment() {

    private var photoUri: Uri? = null
    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            launchCamera()
        } else {
            Toast.makeText(requireContext(), R.string.camera_permission, Toast.LENGTH_SHORT).show()
        }
    }
    private val takePicture = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        val uri = photoUri
        if (success && uri != null) {
            showPhoto(uri)
        }
    }
    private val pickImage = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            showPhoto(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.buttonTakePhoto).setOnClickListener {
            val granted = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            if (granted) {
                launchCamera()
            } else {
                requestCameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
        view.findViewById<View>(R.id.buttonPickGallery).setOnClickListener {
            pickImage.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    }

    private fun launchCamera() {
        val directory = File(requireContext().cacheDir, "camera").apply { mkdirs() }
        val file = File(directory, "preview.jpg").apply { createNewFile() }
        val uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            file
        )
        photoUri = uri
        takePicture.launch(uri)
    }

    private fun showPhoto(uri: Uri) {
        val view = view ?: return
        view.findViewById<ImageView>(R.id.imagePreview).load(uri)
        view.findViewById<View>(R.id.previewEmpty).visibility = View.GONE
        val mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity())
        )[MainViewModel::class.java]
        val result = mainViewModel.recognize()
        showResult(view, result.first, result.second)
    }

    private fun showResult(view: View, scene: SceneType, places: List<Place>) {
        view.findViewById<View>(R.id.recognizeResult).visibility = View.VISIBLE
        val label = getString(sceneLabel(scene))
        view.findViewById<TextView>(R.id.textSceneChip).text =
            label.replaceFirstChar { it.uppercaseChar() }
        val container = view.findViewById<LinearLayout>(R.id.listSimilar)
        val empty = view.findViewById<View>(R.id.textSimilarEmpty)
        container.removeAllViews()
        empty.visibility = if (places.isEmpty()) View.VISIBLE else View.GONE
        val inflater = LayoutInflater.from(container.context)
        places.forEach { place ->
            val row = inflater.inflate(R.layout.item_trip, container, false)
            row.findViewById<TextView>(R.id.textTripName).text = place.name
            row.findViewById<TextView>(R.id.textTripCountry).text =
                getString(R.string.recognize_similar, place.country)
            row.findViewById<ImageView>(R.id.imageTrip).load(place.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.bg_place_placeholder)
                error(R.drawable.bg_place_placeholder)
            }
            row.setOnClickListener { PlaceFragment.open(parentFragmentManager, place) }
            container.addView(row)
        }
    }

    private fun sceneLabel(scene: SceneType): Int = when (scene) {
        SceneType.MOUNTAINS -> R.string.scene_mountains
        SceneType.SEA -> R.string.scene_sea
        SceneType.FOREST -> R.string.scene_forest
        SceneType.CITY -> R.string.scene_city
    }
}
