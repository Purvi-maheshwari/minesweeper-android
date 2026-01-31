package com.example.minesweeper.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.minesweeper.data.model.Cell
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun numberColor(count: Int): Color = when (count) {
    1 -> Color.Blue
    2 -> Color(0xFF388E3C)
    3 -> Color.Red
    4 -> Color(0xFF512DA8)
    5 -> Color(0xFF7B1FA2)
    else -> Color.Black
}

@Composable
fun MineCell(
    cell: Cell,
    onClick: (Int, Int) -> Unit,
    onLongClick: (Int, Int) -> Unit
) {
    val backgroundColor = when {
        cell.isRevealed && cell.isMine -> Color(0xFFD32F2F)
        cell.isRevealed -> Color(0xFFE0E0E0)
        else -> Color(0xFF424242)
    }

    Box(
        modifier = Modifier
            .size(32.dp)
            .border(
                width = if (!cell.isRevealed) 1.dp else 0.dp,
                color = Color.Black
            )
            .background(backgroundColor)
            .combinedClickable(
                onClick = { onClick(cell.row, cell.col) },
                onLongClick = { onLongClick(cell.row, cell.col) }
            ),
        contentAlignment = Alignment.Center
    ) {

        if (cell.isRevealed && cell.isMine) {
            Text("💣", fontSize = 18.sp)
        }

        if (cell.isRevealed && !cell.isMine && cell.adjacentMines > 0) {
            Text(
                text = cell.adjacentMines.toString(),
                fontWeight = FontWeight.Bold,
                color = numberColor(cell.adjacentMines)
            )
        }

        if (cell.isFlagged && !cell.isRevealed) {
            Text("🚩", fontSize = 18.sp)
        }
    }
}

