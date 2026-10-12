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

    override fun saveProfile(login: String, nickname: String, firstName: String, lastName: String) {
        val key = profileKey(login)
        prefs.edit()
            .putString(key + SUFFIX_NICK, nickname)
            .putString(key + SUFFIX_FIRST, firstName)
            .putString(key + SUFFIX_LAST, lastName)
            .apply()
    }

    override fun getNickname(login: String): String {
        return prefs.getString(profileKey(login) + SUFFIX_NICK, "").orEmpty()
    }

    override fun getFirstName(login: String): String {
        return prefs.getString(profileKey(login) + SUFFIX_FIRST, "").orEmpty()
    }

    override fun getLastName(login: String): String {
        return prefs.getString(profileKey(login) + SUFFIX_LAST, "").orEmpty()
    }

    override fun clear() {
        prefs.edit().remove(KEY_LOGIN).apply()
    }

    private fun profileKey(login: String): String {
        return "profile_" + login.trim().lowercase()
    }

    private companion object {
        const val PREFS_NAME = "sway_client"
        const val KEY_LOGIN = "client_login"
        const val SUFFIX_NICK = "_nick"
        const val SUFFIX_FIRST = "_first"
        const val SUFFIX_LAST = "_last"
    }
}
