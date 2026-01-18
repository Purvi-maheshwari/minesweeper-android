package com.example.minesweeper.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.minesweeper.data.model.GameState
import com.example.minesweeper.game.MinesweeperEngine

class GameViewModel : ViewModel() {

    private val _gameState = mutableStateOf(createNewGame())
    val gameState = _gameState

    private fun createNewGame(): GameState {
        val rows = 9
        val cols = 9
        val mines = 10

        val board = MinesweeperEngine.generateBoard(rows, cols, mines)

        return GameState(
            rows = rows,
            cols = cols,
            mineCount = mines,
            board = board
        )
    }
}
