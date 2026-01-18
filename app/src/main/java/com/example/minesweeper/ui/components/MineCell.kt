package com.example.minesweeper.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
fun MineCell(
    cell: Cell,
    onClick: (Int, Int) -> Unit,
    onLongClick: (Int, Int) -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Black)
            .background(
                if (cell.isRevealed) Color.LightGray else Color.DarkGray
            )
            .combinedClickable(
                onClick = { onClick(cell.row, cell.col) },
                onLongClick = { onLongClick(cell.row, cell.col) }
            ),
        contentAlignment = Alignment.Center
    ) {
        when {
            cell.isFlagged -> Text("🚩")
            cell.isRevealed && cell.isMine -> Text("💣")
            cell.isRevealed && cell.adjacentMines > 0 ->
                Text(cell.adjacentMines.toString())
        }
    }
}
