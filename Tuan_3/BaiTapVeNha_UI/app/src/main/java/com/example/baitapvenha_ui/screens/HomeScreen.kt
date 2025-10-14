package com.example.baitapvenha_ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baitapvenha_ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class) // 👈 thêm dòng này
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("UI Components List") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            ComponentItem("Text", "Displays text") { navController.navigate(Screen.Text.route) }
            ComponentItem("Image", "Displays an image") { navController.navigate(Screen.Image.route) }
            ComponentItem("TextField", "Input field for text") { navController.navigate(Screen.TextField.route) }
            ComponentItem("Row", "Arranges elements horizontally") { navController.navigate(Screen.Row.route) }
        }
    }
}
