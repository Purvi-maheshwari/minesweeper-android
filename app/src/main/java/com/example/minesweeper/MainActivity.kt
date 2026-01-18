package com.example.minesweeper

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.minesweeper.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    // Day 2: create ViewModel manually (safe)
    val gameViewModel = GameViewModel()

    // Verify logic
    Log.d(
        "Minesweeper",
        gameViewModel.gameState.value.board.toString()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "HELLO MINESWEEPER",
            color = Color.Black
        )
    }
}
