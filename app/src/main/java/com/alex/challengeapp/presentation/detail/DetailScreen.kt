package com.alex.challengeapp.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alex.challengeapp.presentation.detail.components.HeaderDetail
import com.alex.challengeapp.presentation.detail.components.Overview
import com.alex.challengeapp.presentation.detail.components.ReleaseDate
import com.alex.challengeapp.presentation.detail.components.VoteAverage

@Composable
fun DetailScreen(
    detailVM: DetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state = detailVM.detailState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderDetail(
            modifier = Modifier.fillMaxWidth(),
            title = state.value.movie.title,
            imagePath = state.value.movie.posterPath,
            onBack = onNavigateBack
        )
        ReleaseDate(
            modifier = Modifier.fillMaxWidth(),
            releaseDate = state.value.movie.releaseDate
        )
        VoteAverage(average = state.value.movie.voteAverage)
        Overview(modifier = Modifier.fillMaxWidth(), overview = state.value.movie.overview)
    }
}