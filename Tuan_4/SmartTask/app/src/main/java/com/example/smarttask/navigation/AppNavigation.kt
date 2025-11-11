package com.example.smarttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// Import các màn hình mới
import com.example.smarttask.screens.LoginScreen
import com.example.smarttask.screens.ProfileScreen
import com.example.smarttask.screens.TaskDetailScreen
import com.example.smarttask.screens.TaskListScreen
// Import các màn hình cũ
import com.example.smarttask.screens.ConfirmScreen
import com.example.smarttask.screens.ForgotPasswordScreen
import com.example.smarttask.screens.ResetPasswordScreen
import com.example.smarttask.screens.VerifyCodeScreen

object AppRoutes {
    const val LOGIN = "login"
    const val PROFILE = "profile"
    const val FORGOT_PASSWORD = "forgot_password"
    const val VERIFY_CODE = "verify_code/{email}"
    const val RESET_PASSWORD = "reset_password/{email}"
    const val CONFIRM = "confirm/{email}/{password}"

    // THÊM 2 ROUTE MỚI
    const val TASK_LIST = "task_list" // Trang chủ mới
    const val TASK_DETAIL = "task_detail/{taskId}" // Route cho màn hình chi tiết
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Giữ màn hình bắt đầu là LOGIN
    NavHost(navController = navController, startDestination = AppRoutes.LOGIN) {

        // Cập nhật: Sau khi Login, đi đến TASK_LIST
        composable(AppRoutes.LOGIN) {
            // LoginScreen sẽ gọi:
            // navController.navigate(AppRoutes.TASK_LIST)
            LoginScreen(navController = navController)
        }

        // Màn hình Trang chủ (Danh sách Task) (MỚI)
        composable(AppRoutes.TASK_LIST) {
            TaskListScreen(navController = navController)
        }

        // Màn hình Chi tiết Task (MỚI)
        composable(
            route = AppRoutes.TASK_DETAIL,
            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
        ) { backStackEntry ->
            // Lấy taskId từ đường dẫn
            val taskId = backStackEntry.arguments?.getString("taskId")
            if (taskId != null) {
                TaskDetailScreen(navController = navController, taskId = taskId)
            } else {
                // Xử lý lỗi nếu không có taskId
                navController.popBackStack()
            }
        }

        // --- CÁC FLOW CŨ VẪN GIỮ NGUYÊN ---
        composable(AppRoutes.PROFILE) {
            ProfileScreen(navController = navController)
        }
        composable(AppRoutes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(navController)
        }
        // ... (giữ nguyên các composable cũ khác: Verify, Reset, Confirm)
        composable(
            route = AppRoutes.VERIFY_CODE,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController, email)
        }
        composable(
            route = AppRoutes.RESET_PASSWORD,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ResetPasswordScreen(navController, email)
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
            ConfirmScreen(navController, email, password)
        }
    }
}