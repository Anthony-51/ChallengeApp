package com.alex.challengeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.alex.challengeapp.domain.model.NavigationScreen
import com.alex.challengeapp.presentation.components.MovieNavHost
import com.alex.challengeapp.ui.theme.ChallengeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                  ChallengeAppTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                              modifier = Modifier.fillMaxSize(),
                              color = MaterialTheme.colorScheme.background
                        ) {
                              val navController = rememberNavController()
                              MovieNavHost(navController = navController, startDestination = NavigationScreen.LoginScreen.route)
                        }
                  }
            }
      }
}
