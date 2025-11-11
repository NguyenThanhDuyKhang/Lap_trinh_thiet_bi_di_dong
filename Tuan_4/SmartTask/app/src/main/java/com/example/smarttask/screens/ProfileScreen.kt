package com.example.smarttask.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.smarttask.R
import com.example.smarttask.navigation.AppRoutes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavController) {
    // Lấy thông tin người dùng hiện tại từ Firebase
    val user = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user == null) {
            // Xử lý lỗi
            Text("Không tìm thấy thông tin người dùng.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(AppRoutes.LOGIN) {
                    popUpTo(AppRoutes.PROFILE) { inclusive = true }
                }
            }) {
                Text("Quay về Đăng nhập")
            }
        } else {
            // Hiển thị thông tin
            Text("Profile", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(24.dp))

            // Ảnh đại diện (tải từ URL của Google)
            AsyncImage(
                model = user.photoUrl,
                contentDescription = "Ảnh đại diện",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                // Ảnh tạm trong lúc chờ tải
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Tên
            Text(user.displayName ?: "N/A", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(32.dp))

            // Email
            OutlinedTextField(
                value = user.email ?: "N/A",
                onValueChange = {},
                label = { Text("Email") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Giả lập ô "Date of Birth" bằng User ID
            OutlinedTextField(
                value = user.uid.take(15) + "...", // Hiển thị 1 phần ID
                onValueChange = {},
                label = { Text("Firebase User ID") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f)) // Đẩy nút Back xuống dưới

            // Nút Back
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }
}