package com.example.minesweeper.ui.screen.game

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minesweeper.ui.util.formatTime
import com.example.minesweeper.viewmodel.GameViewModel

@Composable
fun GameScreen(
    difficulty: String,
    onHomeClick: () -> Unit
) {
    val gameViewModel: GameViewModel = viewModel()
    val state = gameViewModel.gameState.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TextButton(onClick = onHomeClick) {
                Text("Home")
            }

            Text(
                text = formatTime(state.elapsedTime),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            GameBoardScreen(gameViewModel)
        }

        Button(
            onClick = {
                gameViewModel.restart(
                    when (difficulty) {
                        "MEDIUM" -> GameViewModel.Difficulty.MEDIUM
                        "HARD" -> GameViewModel.Difficulty.HARD
                        else -> GameViewModel.Difficulty.EASY
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text("Restart")
        }
    }

    if (state.isGameOver) {
        GameOverOverlay(
            isWin = state.isWin,
            time = state.elapsedTime,
            onRestart = {
                gameViewModel.restart(
                    when (difficulty) {
                        "MEDIUM" -> GameViewModel.Difficulty.MEDIUM
                        "HARD" -> GameViewModel.Difficulty.HARD
                        else -> GameViewModel.Difficulty.EASY
                    }
                )
            }
        )
    }
}
