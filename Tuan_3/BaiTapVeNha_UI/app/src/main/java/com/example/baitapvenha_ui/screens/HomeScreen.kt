package com.example.baitapvenha_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitapvenha_ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Trang chủ") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "Logo UTH",
                modifier = Modifier.size(120.dp)
            )

            Text(
                "Chọn chức năng:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Button(onClick = { navController.navigate("list") }) {
                Text("Danh sách")
            }

            Button(onClick = { navController.navigate("textfield") }) {
                Text("TextField")
            }

            Button(onClick = { navController.navigate("rowlayout") }) {
                Text("RowLayout")
            }
        }
    }
}
