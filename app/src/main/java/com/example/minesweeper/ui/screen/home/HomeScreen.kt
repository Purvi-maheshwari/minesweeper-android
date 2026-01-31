package com.example.minesweeper.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class Difficulty {
    EASY, MEDIUM, HARD
}

@Composable
fun HomeScreen(
    username: String,
    highScore: Int,
    onStartGame: (Difficulty) -> Unit,
    onLogout: () -> Unit
) {
    var selectedDifficulty by remember { mutableStateOf<Difficulty?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Minesweeper", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Welcome, $username")

        Spacer(modifier = Modifier.height(24.dp))
        Text("High Score: $highScore")

        Spacer(modifier = Modifier.height(32.dp))
        Text("Select Difficulty")

        Spacer(modifier = Modifier.height(12.dp))

        Difficulty.values().forEach { difficulty ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedDifficulty == difficulty,
                    onClick = { selectedDifficulty = difficulty }
                )
                Text(difficulty.name)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            enabled = selectedDifficulty != null,
            onClick = {
                selectedDifficulty?.let { onStartGame(it) }
            }
        ) {
            Text("Start Game")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onLogout) {
            Text("Logout")
        }
    }
}
