package ru.mirea.kornilov.sway.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.sway.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireActivity())
        )[MainViewModel::class.java]
        viewModel.getAvatarLetter().observe(viewLifecycleOwner) { letter ->
            view.findViewById<TextView>(R.id.textProfileAvatar).text = letter
        }
        val avatarView = view.findViewById<TextView>(R.id.textProfileAvatar)
        val nameView = view.findViewById<TextView>(R.id.textProfileLogin)
        val nicknameView = view.findViewById<TextView>(R.id.textProfileNickname)
        val emailView = view.findViewById<TextView>(R.id.textProfileEmail)
        val subtitleView = view.findViewById<TextView>(R.id.textGuestSubtitle)
        val guestLogin = view.findViewById<View>(R.id.buttonGuestLogin)
        val guestRegister = view.findViewById<View>(R.id.buttonGuestRegister)
        val settingsButton = view.findViewById<View>(R.id.buttonSettings)
        val tripsButton = view.findViewById<View>(R.id.buttonMyTrips)
        val logoutButton = view.findViewById<View>(R.id.buttonLogout)
        viewModel.getAuthorized().observe(viewLifecycleOwner) { authorized ->
            val userMode = if (authorized) View.VISIBLE else View.GONE
            val guestMode = if (authorized) View.GONE else View.VISIBLE
            subtitleView.visibility = guestMode
            guestLogin.visibility = guestMode
            guestRegister.visibility = guestMode
            settingsButton.visibility = userMode
            tripsButton.visibility = userMode
            logoutButton.visibility = userMode
            if (authorized) {
                avatarView.setBackgroundResource(R.drawable.bg_avatar)
                avatarView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            } else {
                avatarView.setBackgroundResource(R.drawable.bg_avatar_guest)
                avatarView.setTextColor(ContextCompat.getColor(requireContext(), R.color.sway_muted))
                avatarView.text = "?"
                nameView.setText(R.string.guest_name)
                nicknameView.visibility = View.GONE
                emailView.visibility = View.GONE
            }
        }
        viewModel.getDisplayName().observe(viewLifecycleOwner) { name ->
            if (viewModel.getAuthorized().value != true) {
                return@observe
            }
            val email = viewModel.getLogin().value.orEmpty()
            nameView.text = name.ifBlank { email }
            emailView.text = email
            emailView.visibility = if (name.isBlank()) View.GONE else View.VISIBLE
        }
        viewModel.getNickname().observe(viewLifecycleOwner) { nickname ->
            if (viewModel.getAuthorized().value != true) {
                return@observe
            }
            nicknameView.text = getString(R.string.profile_nickname, nickname)
            nicknameView.visibility = if (nickname.isBlank()) View.GONE else View.VISIBLE
        }
        guestLogin.setOnClickListener { openAuth(register = false) }
        guestRegister.setOnClickListener { openAuth(register = true) }
        view.findViewById<View>(R.id.buttonSettings).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, SettingsFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<View>(R.id.buttonMyTrips).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, TripsFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        }
        logoutButton.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun openAuth(register: Boolean) {
        startActivity(
            Intent(requireContext(), AuthActivity::class.java)
                .putExtra(AuthActivity.EXTRA_REGISTER, register)
        )
        requireActivity().finish()
    }
}
