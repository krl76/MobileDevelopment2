package ru.mirea.kornilov.resultapifragmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class DataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_data, container, false)
        val button = view.findViewById<Button>(R.id.buttonOpenBottomSheet)
        button.setOnClickListener {
            val text = view.findViewById<EditText>(R.id.editTextInfo).text.toString()
            val bundle = Bundle()
            bundle.putString(BottomSheetFragment.BUNDLE_KEY, text)
            childFragmentManager.setFragmentResult(BottomSheetFragment.REQUEST_KEY, bundle)
            BottomSheetFragment().show(childFragmentManager, "ModalBottomSheet")
        }
        return view
    }
}
