package com.example.minesweeper.data.model

data class GameState(
    val rows: Int = 9,
    val cols: Int = 9,
    val mineCount: Int = 10,
    val board: List<Cell> = emptyList(),
    val isGameOver: Boolean = false,
    val isWin: Boolean = false
)
