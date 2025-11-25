package com.example.quanlythuvien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.example.quanlythuvien.model.LibraryManager
import com.example.quanlythuvien.screens.*
import com.example.quanlythuvien.screens.components.BottomNavBar
import com.example.quanlythuvien.ui.theme.QuanLyThuVienTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanLyThuVienTheme {
                val navController = rememberNavController()
                val manager = remember { LibraryManager() }

                Scaffold(
                    bottomBar = {
                        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                        BottomNavBar(navController, currentRoute)
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "library",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("library") { LibraryScreen(manager) }
                        composable("books") { BookListScreen(manager) }
                        composable("students") { StudentListScreen(manager) }
                    }
                }
            }
        }

    }
}
