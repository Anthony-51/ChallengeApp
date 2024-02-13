package com.alex.challengeapp.domain.model

sealed class NavigationScreen(
      val route: String
) {
      data object LoginScreen : NavigationScreen(route = "login_screen")
      data object HomeScreen : NavigationScreen(route = "home_screen")
      data object DetailScreen : NavigationScreen(route = "detail_screen")
}