package com.example.minesweeper.ui.screen.home

import android.R.color.white
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    username: String,
    bestTimeText: String = "--",
    onStartGame: (String) -> Unit,
    onLogout: () -> Unit
) {
    var selectedDifficulty by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF6A5ACD),
                        Color(0xFF8A7CFF)
                    )
                )
            )
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(32.dp))

            Text(
                text = "MINESWEEPER",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Welcome, $username 👋",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 16.sp
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "BEST TIME",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
            Text(
                text = bestTimeText,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(40.dp))

            DifficultyButton("EASY", selectedDifficulty) {
                selectedDifficulty = "EASY"
            }

            Spacer(Modifier.height(14.dp))

            DifficultyButton("MEDIUM", selectedDifficulty) {
                selectedDifficulty = "MEDIUM"
            }

            Spacer(Modifier.height(14.dp))

            DifficultyButton("HARD", selectedDifficulty) {
                selectedDifficulty = "HARD"
            }

            Spacer(Modifier.height(40.dp))

            val enabled = selectedDifficulty != null
            val startColor by animateColorAsState(
                if (enabled) Color(0xFF00E676) else Color(0xFFBDBDBD),
                label = "startColor"
            )

            Button(
                onClick = {
                    selectedDifficulty?.let { onStartGame(it) }
                },
                enabled = enabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = startColor)
            ) {
                Text(
                    text = "START GAME",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(Modifier.weight(1f))

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.dp
                )
            ) {
                Text(
                    text = "LOGOUT",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

        }
    }
}

@Composable
private fun DifficultyButton(
    label: String,
    selectedDifficulty: String?,
    onClick: () -> Unit
) {
    val selected = selectedDifficulty == label

    val bgColor by animateColorAsState(
        if (selected) Color(0xFFFFC107) else Color.White,
        label = "difficultyBg"
    )

    val textColor by animateColorAsState(
        if (selected) Color.Black else Color(0xFF333333),
        label = "difficultyText"
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = bgColor)
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}
