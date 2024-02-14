package com.alex.challengeapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alex.challengeapp.domain.model.Movie
import com.alex.challengeapp.presentation.home.components.MovieItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    homeVM: HomeViewModel = hiltViewModel(),
    onNavigateToDetails: (Movie) -> Unit
) {
    val state = homeVM.homeState.collectAsState()

    LaunchedEffect(key1 = true) {
        homeVM.effect.onEach { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetails -> {
                    onNavigateToDetails(effect.movie)
                }
                is HomeEffect.ShowToast -> {

                }
            }
        }.collect()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "Movies",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        LazyColumn {
            items(state.value.movies) { movie ->
                MovieItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    movie = movie,
                    onClick = { homeVM.onEvent(HomeEvent.OnMovieClick(movie)) }
                )
            }
        }
    }
}