package com.example.minesweeper.data.session

import android.content.Context

class SessionManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("minesweeper_prefs", Context.MODE_PRIVATE)

    fun saveLogin(username: String) {
        prefs.edit()
            .putBoolean("logged_in", true)
            .putString("username", username)
            .apply()
    }

    fun isLoggedIn(): Boolean =
        prefs.getBoolean("logged_in", false)

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun getUsername(): String =
        prefs.getString("username", "") ?: ""
}
