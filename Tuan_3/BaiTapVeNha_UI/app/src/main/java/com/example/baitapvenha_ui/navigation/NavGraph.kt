package com.example.baitapvenha_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baitapvenha_ui.screens.*

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Text : Screen("text")
    object Image : Screen("image")
    object TextField : Screen("textfield")
    object Row : Screen("row")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Text.route) { TextScreen() }
        composable(Screen.Image.route) { ImageScreen() }
        composable(Screen.TextField.route) { TextFieldScreen() }
        composable(Screen.Row.route) { RowScreen() }
    }
}
