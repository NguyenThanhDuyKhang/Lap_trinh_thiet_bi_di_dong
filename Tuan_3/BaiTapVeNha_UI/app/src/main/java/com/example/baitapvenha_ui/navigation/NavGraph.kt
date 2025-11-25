package com.example.baitapvenha_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitapvenha_ui.screens.HomeScreen
import com.example.baitapvenha_ui.screens.ComponentsListScreen
import com.example.baitapvenha_ui.screens.TextScreen
import com.example.baitapvenha_ui.screens.ImagesScreen
import com.example.baitapvenha_ui.screens.TextFieldScreen
import com.example.baitapvenha_ui.screens.RowLayoutScreen
@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("components") { ComponentsListScreen(navController) }
        composable("text") { TextScreen(navController) }
        composable("image") { ImagesScreen(navController) }
        composable("textfield") { TextFieldScreen(navController) }
        composable("rowlayout") { RowLayoutScreen(navController) }
    }
}
