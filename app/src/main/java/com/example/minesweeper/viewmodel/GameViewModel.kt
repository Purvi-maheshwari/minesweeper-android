package com.example.minesweeper.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.minesweeper.data.model.Cell
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

    fun onCellClick(row: Int, col: Int) {
        val index = row * _gameState.value.cols + col
        val cell = _gameState.value.board[index]

        if (cell.isRevealed || cell.isFlagged) return

        updateCell(cell.copy(isRevealed = true))
    }

    fun onCellLongClick(row: Int, col: Int) {
        val index = row * _gameState.value.cols + col
        val cell = _gameState.value.board[index]

        if (cell.isRevealed) return

        updateCell(cell.copy(isFlagged = !cell.isFlagged))
    }

    private fun updateCell(updated: Cell) {
        val newBoard = _gameState.value.board.map {
            if (it.row == updated.row && it.col == updated.col) updated else it
        }

        _gameState.value = _gameState.value.copy(board = newBoard)
    }
}
