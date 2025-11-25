package com.example.baitapvenha_ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách Components") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Các thành phần cơ bản trong Jetpack Compose:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            ComponentItem(
                name = "Text",
                description = "Hiển thị chuỗi văn bản lên giao diện.",
                onClick = { navController.navigate("text") }
            )

            ComponentItem(
                name = "Image",
                description = "Hiển thị hình ảnh từ tài nguyên hoặc URL.",
                onClick = { navController.navigate("image") }
            )

            ComponentItem(
                name = "TextField",
                description = "Ô nhập văn bản từ người dùng.",
                onClick = { navController.navigate("textfield") }
            )

            ComponentItem(
                name = "Row / Column",
                description = "Bố cục sắp xếp các thành phần theo hàng hoặc cột.",
                onClick = { navController.navigate("rowlayout") }
            )
        }
    }
}

@Composable
fun ComponentItem(
    name: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description)
        }
    }
}
