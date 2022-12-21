package com.benshapiro.sacp.navigation

import Screen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benshapiro.sacp.screen.auth.AuthScreen
import com.benshapiro.sacp.screen.auth.AuthScreenViewModel
import com.benshapiro.sacp.screen.main.MainScreen
import com.benshapiro.sacp.screen.main.MainScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.AuthScreen.route) {
        composable(route = Screen.MainScreen.route,) {
            val vm = hiltViewModel<MainScreenViewModel>()
            MainScreen(navController = navController)
        }
        composable(route = Screen.AuthScreen.route) {
            val vm = hiltViewModel<AuthScreenViewModel>()
            AuthScreen(navController = navController)
        }
    }
}