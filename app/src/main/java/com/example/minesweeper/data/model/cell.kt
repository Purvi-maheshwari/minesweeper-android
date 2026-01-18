package com.example.minesweeper.data.model

data class Cell(
    val row: Int,
    val col: Int,
    val isMine: Boolean = false,
    val isRevealed: Boolean = false,
    val isFlagged: Boolean = false,
    val adjacentMines: Int = 0
)
