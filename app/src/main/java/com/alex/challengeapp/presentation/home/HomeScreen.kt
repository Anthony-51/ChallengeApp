package com.alex.challengeapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
      val context = LocalContext.current
      val elements = homeVM.moviesPager.collectAsLazyPagingItems()
      val listState = rememberLazyListState()
      val isAtBottom by remember {
            derivedStateOf {
                  listState.isScrolledToTheEnd()
            }
      }

      LaunchedEffect(key1 = true, key2 = elements.loadState) {
            if (elements.loadState.refresh is LoadState.Error) homeVM.onEvent(HomeEvent.OnError("Sin conexión a internet"))
            homeVM.effect.onEach { effect ->
                  when (effect) {
                        is HomeEffect.NavigateToDetails -> {
                              onNavigateToDetails(effect.movie)
                        }

                        is HomeEffect.ShowToast -> {
                              Toast.makeText(
                                    context,
                                    effect.message,
                                    Toast.LENGTH_LONG
                              ).show()
                        }
                  }
            }.collect()
      }
      Box(
            modifier = Modifier
                  .fillMaxSize()
                  .background(Color.White),
      ) {
            if (elements.loadState.refresh is LoadState.Loading) {
                  CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                  )
            } else {
                  LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                  ) {
                        item {
                              Text(
                                    modifier = Modifier.padding(start = 8.dp),
                                    text = "Movies",
                                    style = TextStyle(
                                          fontSize = 24.sp,
                                          fontWeight = FontWeight.Bold,
                                          color = Color.Black
                                    )
                              )
                        }
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
            }
      }
}

fun LazyListState.isScrolledToTheEnd() =
      layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
