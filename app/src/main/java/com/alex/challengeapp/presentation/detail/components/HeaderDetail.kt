package com.alex.challengeapp.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.alex.challengeapp.R

@Composable
fun HeaderDetail(
    modifier: Modifier = Modifier,
    title: String,
    imagePath: String,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
        SubcomposeAsyncImage(
            modifier = Modifier.size(width = 120.dp, height = 200.dp),
            model = imagePath,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator()
            },
            error = {
                Image(painter = painterResource(id = R.drawable.error_logo), contentDescription = "Error")
            }
        )
    }
}