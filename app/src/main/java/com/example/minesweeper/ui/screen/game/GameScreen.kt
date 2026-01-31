package com.example.minesweeper.ui.screen.game

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minesweeper.ui.components.MineCell
import com.example.minesweeper.viewmodel.GameViewModel

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val state = gameViewModel.gameState.value

    Column(modifier = Modifier.padding(2.dp,20.dp)) {

        Row {
            Button(onClick = {
                gameViewModel.restart(GameViewModel.Difficulty.EASY)
            }) {
                Text("Easy")
            }

            Button(onClick = { gameViewModel.restart(GameViewModel.Difficulty.MEDIUM) }) {
                Text("Medium")
            }
            Button(onClick = { gameViewModel.restart(GameViewModel.Difficulty.HARD) }) {
                Text("Hard")
            }
            Button(onClick = { gameViewModel.restart() }) {
                Text("Restart")
            }
        }


        key(state.gameId) {

            val verticalScroll = rememberScrollState()
            val horizontalScroll = rememberScrollState()

            Box(
                modifier = Modifier
                    .weight(1f) // take remaining screen
                    .verticalScroll(verticalScroll)
                    .horizontalScroll(horizontalScroll)
            ) {
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
        }

        if (state.isGameOver) {
            Text(
                text = if (state.isWin) "🎉 You Win!" else "💥 Game Over"
            )
        }
    }
}