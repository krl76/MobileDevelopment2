package ru.mirea.kornilov.sway.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.models.AuthError

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity())
        )[MainViewModel::class.java]
        val firstNameField = view.findViewById<EditText>(R.id.editFirstName)
        val lastNameField = view.findViewById<EditText>(R.id.editLastName)
        val nicknameField = view.findViewById<EditText>(R.id.editNickname)
        val statusView = view.findViewById<TextView>(R.id.textSettingsStatus)
        view.findViewById<TextView>(R.id.textSettingsEmail).text = viewModel.getLogin().value.orEmpty()
        if (savedInstanceState == null) {
            firstNameField.setText(viewModel.getFirstName().value.orEmpty())
            lastNameField.setText(viewModel.getLastName().value.orEmpty())
            nicknameField.setText(viewModel.getNickname().value.orEmpty())
        }
        view.findViewById<View>(R.id.buttonSaveProfile).setOnClickListener {
            val firstName = firstNameField.text.toString()
            val lastName = lastNameField.text.toString()
            val nickname = nicknameField.text.toString()
            if (firstName.isBlank() || lastName.isBlank() || nickname.isBlank()) {
                statusView.setText(R.string.profile_required)
                return@setOnClickListener
            }
            statusView.text = ""
            viewModel.updateProfile(nickname, firstName, lastName) { failure ->
                if (failure == null) {
                    parentFragmentManager.popBackStack()
                    return@updateProfile
                }
                statusView.setText(
                    if (failure.error == AuthError.PROFILE_NOT_SAVED) {
                        R.string.auth_profile_failed
                    } else {
                        R.string.auth_network
                    }
                )
                if (failure.detail.isNotBlank()) {
                    statusView.append("\n")
                    statusView.append(failure.detail)
                }
            }
        }
    }
}
