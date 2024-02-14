package com.alex.challengeapp.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun VoteAverage(
    modifier: Modifier = Modifier,
    average: Double
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Nota promedio:", style = TextStyle(fontSize = 16.sp))
        Text(text = "$average", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black))
    }
}