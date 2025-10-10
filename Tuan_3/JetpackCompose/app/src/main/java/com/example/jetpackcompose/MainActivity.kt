package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

// 🔹 Activity chính của app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent là nơi dựng toàn bộ UI bằng Compose
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeIntroScreen() // Gọi composable hiển thị UI
                }
            }
        }
    }
}

// 🔹 Màn hình giao diện chính (Compose function)
@Composable
fun ComposeIntroScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 🔸 Phần nội dung giữa màn hình
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Hình logo Jetpack Compose
            Image(
                painter = painterResource(id = R.drawable.logo_compose),
                contentDescription = "Jetpack Compose Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tiêu đề
            Text(
                text = "Jetpack Compose",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Mô tả
            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // 🔸 Nút “I’m ready”
        Button(
            onClick = { /* TODO: xử lý sự kiện khi bấm nút */ },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("I’m ready", color = Color.White, fontSize = 16.sp)
        }
    }
}

// 🔹 Thêm Preview để xem giao diện ngay trong Android Studio
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewComposeIntroScreen() {
    JetpackComposeTheme {
        ComposeIntroScreen()
    }
}
