package com.alex.challengeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.challengeapp.domain.model.ResourceState
import com.alex.challengeapp.domain.use_cases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovies: GetMoviesUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    private val _effect: Channel<HomeEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        getMovies()
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnLoadMoreMovies -> getMovies()
            is HomeEvent.OnMovieClick -> {
                viewModelScope.launch {
                    _effect.send(HomeEffect.NavigateToDetails(event.movie))
                }
            }
        }
    }

    private fun getMovies() {
        getMovies(_homeState.value.currentPage).onEach { result ->
            when(result){
                is ResourceState.Error -> {
                    _homeState.update { it.copy(isLoading = false) }
                }
                ResourceState.Loading -> _homeState.update { it.copy(isLoading = true) }
                is ResourceState.Success -> {
                    _homeState.update {
                        it.copy(
                            isLoading = false,
                            movies = it.movies + result.data.results,
                            currentPage = it.currentPage + 1,
                            totalPages = result.data.totalPages
                        )
                    }
                }
            }
        }
            .launchIn(viewModelScope)
    }
}