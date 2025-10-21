package com.example.smarttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.smarttask.screens.ConfirmScreen
import com.example.smarttask.screens.ForgotPasswordScreen
import com.example.smarttask.screens.ResetPasswordScreen
import com.example.smarttask.screens.VerifyCodeScreen

object AppRoutes {
    const val FORGOT_PASSWORD = "forgot_password"
    const val VERIFY_CODE = "verify_code/{email}"
    const val RESET_PASSWORD = "reset_password/{email}"
    const val CONFIRM = "confirm/{email}/{password}"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.FORGOT_PASSWORD) {

        composable(AppRoutes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(navController = navController)
        }

        composable(
            route = AppRoutes.VERIFY_CODE,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController = navController, email = email)
        }

        composable(
            route = AppRoutes.RESET_PASSWORD,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ResetPasswordScreen(navController = navController, email = email)
        }

        composable(
            route = AppRoutes.CONFIRM,
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ConfirmScreen(navController = navController, email = email, password = password)
        }
    }
}