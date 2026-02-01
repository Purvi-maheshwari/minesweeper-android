package com.example.minesweeper.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minesweeper.data.model.Cell
import com.example.minesweeper.data.model.GameState
import com.example.minesweeper.game.MinesweeperEngine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val _gameState = mutableStateOf(createNewGame(Difficulty.EASY))
    val gameState = _gameState

    private var currentDifficulty = Difficulty.EASY

    enum class Difficulty(val rows: Int, val cols: Int, val mines: Int) {
        EASY(22, 12, 34),
        MEDIUM(22, 12, 54),
        HARD(22, 12, 74)
    }

    init {
        startTimerLoop()
    }
    private fun startTimerIfNeeded() {
        val state = _gameState.value
        if (!state.isTimerRunning) {
            _gameState.value = state.copy(isTimerRunning = true)
        }
    }

    private fun startTimerLoop() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                val state = _gameState.value
                if (state.isTimerRunning && !state.isGameOver) {
                    _gameState.value =
                        state.copy(elapsedTime = state.elapsedTime + 1)
                }
            }
        }
    }

    fun restart(difficulty: Difficulty = currentDifficulty) {
        currentDifficulty = difficulty
        _gameState.value = createNewGame(difficulty)
    }

    private fun createNewGame(difficulty: Difficulty): GameState {
        val board = MinesweeperEngine.generateBoard(
            rows = difficulty.rows,
            cols = difficulty.cols,
            mines = difficulty.mines
        )

        return GameState(
            rows = difficulty.rows,
            cols = difficulty.cols,
            mineCount = difficulty.mines,
            board = board
        )
    }

    fun onCellClick(row: Int, col: Int) {
        val state = _gameState.value
        if (state.isGameOver) return

        startTimerIfNeeded()

        val index = row * state.cols + col
        val cell = state.board[index]

        if (cell.isRevealed || cell.isFlagged) return

        if (cell.isMine) {
            val revealedBoard = state.board.map {
                if (it.isMine) it.copy(isRevealed = true) else it
            }

            _gameState.value = state.copy(board = revealedBoard)

            viewModelScope.launch {
                delay(700)
                _gameState.value = _gameState.value.copy(
                    isGameOver = true,
                    isWin = false,
                    isTimerRunning = false
                )
            }
            return
        }

        val newBoard = revealCells(cell, state)
        updateState(newBoard)
    }

    fun onCellLongClick(row: Int, col: Int) {
        val state = _gameState.value
        if (state.isGameOver) return

        val index = row * state.cols + col
        val cell = state.board[index]

        if (cell.isRevealed) return

        val newBoard = state.board.map {
            if (it.row == row && it.col == col)
                it.copy(isFlagged = !it.isFlagged)
            else it
        }

        _gameState.value = state.copy(board = newBoard)
    }

    private fun revealCells(start: Cell, state: GameState): List<Cell> {
        val board = state.board.toMutableList()
        val stack = mutableListOf(start)

        while (stack.isNotEmpty()) {
            val cell = stack.removeAt(stack.lastIndex)
            val index = cell.row * state.cols + cell.col
            val current = board[index]

            if (current.isRevealed || current.isFlagged) continue

            board[index] = current.copy(isRevealed = true)

            if (current.adjacentMines == 0) {
                for (r in cell.row - 1..cell.row + 1) {
                    for (c in cell.col - 1..cell.col + 1) {
                        if (r in 0 until state.rows && c in 0 until state.cols) {
                            val neighborIndex = r * state.cols + c
                            val neighbor = board[neighborIndex]
                            if (!neighbor.isRevealed && !neighbor.isMine) {
                                stack.add(neighbor)
                            }
                        }
                    }
                }
            }
        }
        return board
    }

    private fun updateState(newBoard: List<Cell>) {
        val state = _gameState.value

        val nonMineCount = newBoard.count { !it.isMine }
        val revealedCount = newBoard.count { !it.isMine && it.isRevealed }

        val win = nonMineCount == revealedCount

        _gameState.value = state.copy(
            board = newBoard,
            isGameOver = win,
            isWin = win,
            isTimerRunning = !win
        )
    }
}
