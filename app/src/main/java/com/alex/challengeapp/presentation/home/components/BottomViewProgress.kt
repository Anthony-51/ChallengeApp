package com.alex.challengeapp.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomViewProgress(
      modifier: Modifier = Modifier
) {
      Box(
            modifier = modifier
      ) {
            CircularProgressIndicator(
                  modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center),
            )
      }
}