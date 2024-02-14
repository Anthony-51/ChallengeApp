package com.alex.challengeapp.presentation.components

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alex.challengeapp.domain.model.Movie
import com.alex.challengeapp.domain.model.NavigationScreen
import com.alex.challengeapp.presentation.detail.DetailScreen
import com.alex.challengeapp.presentation.home.HomeScreen
import com.alex.challengeapp.presentation.login.LoginScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@Composable
fun MovieNavHost(
      navController: NavHostController,
      startDestination: String
) {
      NavHost(navController = navController, startDestination = startDestination) {
            composable(
                  route = NavigationScreen.LoginScreen.route,
                  enterTransition = { return@composable fadeIn(tween(2000)) }
            ) {
                  LoginScreen(
                        onNavigateToHome = {
                              navController.navigate(NavigationScreen.HomeScreen.route) {
                                    popUpTo(NavigationScreen.LoginScreen.route) {
                                          inclusive = true
                                    }
                                    launchSingleTop = true
                              }
                        }
                  )
            }

            composable(
                  route = NavigationScreen.HomeScreen.route,
                  enterTransition = {
                        return@composable slideIntoContainer(
                              AnimatedContentTransitionScope.SlideDirection.Up,
                              tween(1500)
                        )
                  },
                  popEnterTransition = {
                        return@composable slideIntoContainer(
                              AnimatedContentTransitionScope.SlideDirection.Down,
                              tween(1500)
                        )
                  },
                  exitTransition = { return@composable fadeOut(tween(1500)) }
            ) {
                  HomeScreen(
                        onNavigateToDetails = { movie ->
                              navController.navigate(
                                    NavigationScreen.DetailScreen.route + "/${
                                          Uri.encode(
                                                Json.encodeToJsonElement(movie).toString()
                                          )
                                    }"
                              ) {
                                    popUpTo(NavigationScreen.HomeScreen.route) {
                                          saveState = true
                                    }
                                    restoreState = true
                              }
                        }
                  )
            }

            composable(
                  route = NavigationScreen.DetailScreen.route + "/{movie}",
                  arguments = listOf(
                        navArgument("movie") { type = parcelableTypeOf<Movie>() }
                  ),
                  enterTransition = { return@composable fadeIn(tween(1500)) },
                  exitTransition = {
                        return@composable slideOutOfContainer(
                              AnimatedContentTransitionScope.SlideDirection.End, tween(1500)
                        )
                  }
            ) {
                  DetailScreen()
            }
      }
}

inline fun <reified T : Parcelable> parcelableTypeOf() =
      object : NavType<T>(isNullableAllowed = false) {
            override fun get(bundle: Bundle, key: String): T? {
                  return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): T {
                  return Json.decodeFromString(Uri.decode(value))
            }

            override fun put(bundle: Bundle, key: String, value: T) {
                  bundle.putParcelable(key, value)
            }

      }