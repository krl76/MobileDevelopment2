package ru.mirea.kornilov.fragmentapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(BlankFragment::class.java.simpleName, "onCreateView")
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numberStudent = requireArguments().getInt(ARG_STUDENT_NUMBER)
        Log.d(BlankFragment::class.java.simpleName, numberStudent.toString())
        view.findViewById<TextView>(R.id.textStudentNumber).text =
            getString(R.string.student_number, numberStudent)
    }

    companion object {
        const val ARG_STUDENT_NUMBER = "my_number_student"
    }
}
