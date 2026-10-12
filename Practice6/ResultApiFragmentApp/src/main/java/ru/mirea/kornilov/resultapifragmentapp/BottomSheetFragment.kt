package ru.mirea.kornilov.resultapifragmentapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var receivedText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, bundle ->
            receivedText = bundle.getString(BUNDLE_KEY).orEmpty()
            Log.d(BottomSheetFragment::class.java.simpleName, "Get text $receivedText")
            view?.findViewById<TextView>(R.id.textResult)?.text = shownText(receivedText)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textResult).text = shownText(receivedText)
    }

    private fun shownText(text: String): String {
        return text.ifEmpty { getString(R.string.empty_result) }
    }

    companion object {
        const val REQUEST_KEY = "requestKey"
        const val BUNDLE_KEY = "key"
    }
}
