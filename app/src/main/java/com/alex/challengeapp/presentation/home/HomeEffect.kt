package com.alex.challengeapp.presentation.home

import com.alex.challengeapp.domain.model.Movie

interface HomeEffect {
    data class ShowToast(val message: String) : HomeEffect

    data class NavigateToDetails(val movie: Movie) : HomeEffect
}