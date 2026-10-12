package ru.mirea.kornilov.sway.presentation

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.sway.R

class MainActivity : AppCompatActivity() {
    private var authorized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(MainActivity::class.java.simpleName, "MainActivity created")

        val viewModel = ViewModelProvider(this, ViewModelFactory(this))[MainViewModel::class.java]
        viewModel.getAvatarLetter().observe(this) { letter ->
            findViewById<TextView>(R.id.textAvatar).text = letter
        }
        viewModel.getAuthorized().observe(this) { isAuthorized ->
            authorized = isAuthorized
            findViewById<View>(R.id.textAvatar).visibility =
                if (isAuthorized) View.VISIBLE else View.GONE
            renderChrome()
        }
        viewModel.getLoggedOut().observe(this) { loggedOut ->
            if (loggedOut) {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, CatalogFragment::class.java, null)
                .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener { renderChrome() }
        findViewById<View>(R.id.tabCatalog).setOnClickListener { showCatalog() }
        findViewById<View>(R.id.tabTrips).setOnClickListener { showTrips() }
        findViewById<View>(R.id.tabCamera).setOnClickListener { showCamera() }
        findViewById<View>(R.id.tabProfile).setOnClickListener { showProfile() }
        findViewById<View>(R.id.textAvatar).setOnClickListener { showProfile() }
    }

    override fun onStart() {
        super.onStart()
        renderChrome()
    }

    private fun showCatalog() {
        val onCatalog = supportFragmentManager.findFragmentById(R.id.fragment_container) is CatalogFragment
        if (onCatalog && supportFragmentManager.backStackEntryCount == 0) {
            return
        }
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun showTrips() {
        if (!authorized) {
            showLockedSection()
            return
        }
        showTab(TripsFragment::class.java)
    }

    private fun showCamera() {
        if (!authorized) {
            showLockedSection()
            return
        }
        showTab(CameraFragment::class.java)
    }

    private fun showLockedSection() {
        Toast.makeText(this, R.string.guest_section_locked, Toast.LENGTH_SHORT).show()
    }

    private fun showProfile() {
        val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (current is SettingsFragment && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return
        }
        showTab(ProfileFragment::class.java)
    }

    private fun showTab(fragmentClass: Class<out Fragment>) {
        val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (current != null && fragmentClass.isInstance(current)) {
            return
        }
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragment_container, fragmentClass, null)
            .addToBackStack(null)
            .commit()
    }

    private fun renderChrome() {
        val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val place = current is PlaceFragment
        val chrome = if (place) View.GONE else View.VISIBLE
        findViewById<View>(R.id.topBar).visibility = chrome
        findViewById<View>(R.id.tabBar).visibility = chrome
        if (place) {
            return
        }
        val trips = current is TripsFragment
        val camera = current is CameraFragment
        val settings = current is SettingsFragment
        val profile = current is ProfileFragment || settings
        findViewById<TextView>(R.id.textScreenTitle).setText(
            when {
                settings -> R.string.settings_title
                profile -> R.string.tab_profile
                trips -> R.string.trips_title
                camera -> R.string.recognize_title
                else -> R.string.catalog_title
            }
        )
        val lockVisibility = if (authorized) View.GONE else View.VISIBLE
        findViewById<View>(R.id.imageLockTrips).visibility = lockVisibility
        findViewById<View>(R.id.imageLockCamera).visibility = lockVisibility
        tintTab(R.id.imageTabCatalog, R.id.textTabCatalog, active = !trips && !profile && !camera)
        tintTab(
            R.id.imageTabTrips,
            R.id.textTabTrips,
            active = trips,
            enabled = authorized
        )
        tintTab(
            R.id.imageTabCamera,
            R.id.textTabCamera,
            active = camera,
            enabled = authorized
        )
        tintTab(R.id.imageTabProfile, R.id.textTabProfile, active = profile)
    }

    private fun tintTab(iconId: Int, labelId: Int, active: Boolean, enabled: Boolean = true) {
        val color = ContextCompat.getColor(
            this,
            when {
                !enabled -> R.color.sway_locked
                active -> R.color.sway_primary
                else -> R.color.sway_muted
            }
        )
        findViewById<ImageView>(iconId).imageTintList = ColorStateList.valueOf(color)
        findViewById<TextView>(labelId).setTextColor(color)
    }
}
