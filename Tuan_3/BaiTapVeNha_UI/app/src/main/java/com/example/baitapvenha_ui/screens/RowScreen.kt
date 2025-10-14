package com.example.baitapvenha_ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun RowScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Row Layout") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            repeat(3) { // tạo 3 hàng
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    repeat(3) { // mỗi hàng 3 ô vuông
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color(0xFF90CAF9), RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
        }
    }
}
