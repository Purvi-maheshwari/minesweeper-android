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

    fun getHighScore(): Int =
        prefs.getInt("high_score", 0)

    fun saveHighScore(score: Int) {
        val currentHigh = getHighScore()
        if (score > currentHigh) {
            prefs.edit().putInt("high_score", score).apply()
        }
    }
    fun getBestTime(difficulty: String): Int =
        prefs.getInt("best_time_$difficulty", Int.MAX_VALUE)

    fun saveBestTime(difficulty: String, time: Int) {
        val currentBest = getBestTime(difficulty)
        if (time < currentBest) {
            prefs.edit()
                .putInt("best_time_$difficulty", time)
                .apply()
        }
    }

}
