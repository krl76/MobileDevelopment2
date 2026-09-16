package ru.mirea.kornilov.sway.data.storage.sharedprefs

import android.content.Context
import ru.mirea.kornilov.sway.data.storage.ClientStorage

class SharedPrefClientStorage(context: Context) : ClientStorage {
    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveLogin(login: String) {
        prefs.edit().putString(KEY_LOGIN, login).apply()
    }

    override fun getLogin(): String? {
        return prefs.getString(KEY_LOGIN, null)
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }

    private companion object {
        const val PREFS_NAME = "sway_client"
        const val KEY_LOGIN = "client_login"
    }
}
