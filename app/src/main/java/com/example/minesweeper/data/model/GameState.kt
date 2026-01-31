package com.example.minesweeper.data.model

data class GameState(
    val gameId: Long = System.currentTimeMillis(),

    val rows: Int,
    val cols: Int,
    val mineCount: Int,
    val board: List<Cell>,

    val isGameOver: Boolean = false,
    val isWin: Boolean = false,

    val elapsedTime: Int = 0,
    val isTimerRunning: Boolean = false
)
