package com.example.quanlythuvien.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "library",
            onClick = { navController.navigate("library") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Quản lý") },
            label = { Text("Quản lý") }
        )
        NavigationBarItem(
            selected = currentRoute == "books",
            onClick = { navController.navigate("books") },
            icon = { Icon(Icons.Default.List, contentDescription = "DS Sách") },
            label = { Text("DS Sách") }
        )
        NavigationBarItem(
            selected = currentRoute == "students",
            onClick = { navController.navigate("students") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Sinh viên") },
            label = { Text("Sinh viên") }
        )
    }
}
