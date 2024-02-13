package com.alex.challengeapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alex.challengeapp.domain.model.NavigationScreen
import com.alex.challengeapp.presentation.login.LoginScreen

@Composable
fun MovieNavHost(
      navController: NavHostController,
      startDestination: String
) {
      NavHost(navController = navController, startDestination = startDestination){
            composable(route = NavigationScreen.LoginScreen.route){
                  LoginScreen(
                        onNavigateToHome = {
                        }
                  )
            }
      }
}