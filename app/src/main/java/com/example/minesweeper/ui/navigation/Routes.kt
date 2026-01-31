package com.example.minesweeper.ui.navigation

object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val GAME = "game"

    fun gameRoute(difficulty: String) = "$GAME/$difficulty"
}

