package com.example.minesweeper.game

import com.example.minesweeper.data.model.Cell
import kotlin.random.Random

object MinesweeperEngine {

    fun generateBoard(
        rows: Int,
        cols: Int,
        mines: Int
    ): List<Cell> {

        val totalCells = rows * cols
        val minePositions = Random
            .nextInt(totalCells)
            .let { generateSequence { Random.nextInt(totalCells) } }
            .distinct()
            .take(mines)
            .toSet()

        val board = MutableList(totalCells) { index ->
            val row = index / cols
            val col = index % cols
            Cell(
                row = row,
                col = col,
                isMine = minePositions.contains(index)
            )
        }

        return board.map { cell ->
            if (cell.isMine) cell
            else cell.copy(
                adjacentMines = countAdjacentMines(cell, board, rows, cols)
            )
        }
    }

    private fun countAdjacentMines(
        cell: Cell,
        board: List<Cell>,
        rows: Int,
        cols: Int
    ): Int {
        var count = 0

        for (r in cell.row - 1..cell.row + 1) {
            for (c in cell.col - 1..cell.col + 1) {
                if (r == cell.row && c == cell.col) continue
                if (r in 0 until rows && c in 0 until cols) {
                    val index = r * cols + c
                    if (board[index].isMine) count++
                }
            }
        }
        return count
    }
}
