package com.example.minesweeper.ui.screen.game

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minesweeper.viewmodel.GameViewModel

fun formatTime(seconds: Int): String {
    val mins = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", mins, secs)
}
@Composable
fun GameScreen(
    difficulty: String,
    onHomeClick: () -> Unit
) {
    val gameViewModel: GameViewModel = viewModel()
    val state = gameViewModel.gameState.value

    LaunchedEffect(difficulty) {
        when (difficulty) {
            "EASY" -> gameViewModel.restart(GameViewModel.Difficulty.EASY)
            "MEDIUM" -> gameViewModel.restart(GameViewModel.Difficulty.MEDIUM)
            "HARD" -> gameViewModel.restart(GameViewModel.Difficulty.HARD)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    onHomeClick()
                }
            ) {
                Text("Home")
            }

            Text(
                text = "⏱ ${formatTime(state.elapsedTime)}",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            GameBoardScreen(gameViewModel)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                gameViewModel.restart(
                    when (difficulty) {
                        "MEDIUM" -> GameViewModel.Difficulty.MEDIUM
                        "HARD" -> GameViewModel.Difficulty.HARD
                        else -> GameViewModel.Difficulty.EASY
                    }
                )
            }
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
