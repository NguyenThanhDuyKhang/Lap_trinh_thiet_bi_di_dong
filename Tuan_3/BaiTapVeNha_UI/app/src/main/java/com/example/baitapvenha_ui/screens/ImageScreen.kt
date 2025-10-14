package com.example.baitapvenha_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Images") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            // Ảnh từ Internet
            Image(
                painter = rememberAsyncImagePainter("https://giaothongvantaihochiminh.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png"),
                contentDescription = "Logo GTVT",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text("Ảnh từ URL")
        }
    }
}
