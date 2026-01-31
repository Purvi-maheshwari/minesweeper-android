package com.example.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minesweeper.ui.navigation.AppNavGraph
import com.example.minesweeper.ui.screen.game.GameScreen
import com.example.minesweeper.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavGraph()
        }

    }
}
