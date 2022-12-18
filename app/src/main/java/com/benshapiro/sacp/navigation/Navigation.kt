package com.benshapiro.sacp.navigation

import Screen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benshapiro.sacp.screen.MainScreen
import com.benshapiro.sacp.screen.MainScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route,) {
            val vm = hiltViewModel<MainScreenViewModel>()
            MainScreen(navController = navController)
        }
    }
}