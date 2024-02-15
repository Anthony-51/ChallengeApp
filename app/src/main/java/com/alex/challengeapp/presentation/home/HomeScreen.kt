package com.alex.challengeapp.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.alex.challengeapp.domain.model.Movie
import com.alex.challengeapp.presentation.home.components.BottomViewProgress
import com.alex.challengeapp.presentation.home.components.MovieItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import androidx.paging.compose.items

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
      homeVM: HomeViewModel = hiltViewModel(),
      onNavigateToDetails: (Movie) -> Unit
) {
      val state = homeVM.homeState.collectAsState()
      val elements = homeVM.moviesPager.collectAsLazyPagingItems()
      val listState = rememberLazyListState()
      val isAtBottom by remember {
            derivedStateOf {
                  listState.isScrolledToTheEnd()
            }
      }

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
                  style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                  )
            )
            LazyColumn(
                  modifier = Modifier.weight(1f),
                  state = listState
            ) {
                  items(elements) { movie ->
                        if (movie != null) {
                              MovieItem(
                                    modifier = Modifier
                                          .fillMaxWidth()
                                          .padding(8.dp)
                                          .animateItemPlacement(),
                                    movie = movie,
                                    onClick = { homeVM.onEvent(HomeEvent.OnMovieClick(movie)) }
                              )
                        }
                  }
                  item {
                        if (elements.loadState.append is LoadState.Loading) {
                              BottomViewProgress(
                                    modifier = Modifier
                                          .fillMaxWidth()
                                          .padding(vertical = 10.dp)
                              )
                        }
                  }
            }
//            AnimatedVisibility (isAtBottom && !state.value.isLastPage) {
//                  BottomViewProgress(
//                        modifier = Modifier
//                              .fillMaxWidth()
//                              .padding(vertical = 10.dp)
//                  )
//                  if (!state.value.isLoading) {
//                        homeVM.onEvent(HomeEvent.OnLoadMoreMovies)
//                  }
//            }
      }
}

fun LazyListState.isScrolledToTheEnd() =
      layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
