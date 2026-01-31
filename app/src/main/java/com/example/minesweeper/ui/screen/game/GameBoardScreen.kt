package com.example.minesweeper.ui.screen.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.minesweeper.ui.components.MineCell
import com.example.minesweeper.viewmodel.GameViewModel

@Composable
fun GameBoardScreen(gameViewModel: GameViewModel) {
    val state = gameViewModel.gameState.value

    Column {
        repeat(state.rows) { row ->
            Row {
                repeat(state.cols) { col ->
                    val cell = state.board[row * state.cols + col]
                    MineCell(
                        cell = cell,
                        onClick = gameViewModel::onCellClick,
                        onLongClick = gameViewModel::onCellLongClick
                    )
                }
            }
        }
    }
}
