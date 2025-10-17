package com.example.quanlythuvien.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlythuvien.model.LibraryManager
import com.example.quanlythuvien.ui.theme.QuanLyThuVienTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LibraryScreen(manager: LibraryManager) {
    var studentName by remember { mutableStateOf("") }
    var currentStudent by remember { mutableStateOf("") }
    var bookName by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // Danh sách sách mượn của sinh viên hiện tại
    val borrowedBooks = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Tiêu đề
        Text(
            "Hệ thống\nQuản lý Thư viện",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Ô nhập sinh viên + nút thay đổi
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = studentName,
                onValueChange = { studentName = it },
                label = { Text("Sinh viên") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (studentName.isNotBlank()) {
                        currentStudent = studentName
                        message = ""
                        borrowedBooks.clear()
                    }
                }
            ) {
                Text("Thay đổi")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tiêu đề danh sách
        if (currentStudent.isNotBlank()) {
            Text(
                text = "Danh sách sách của $currentStudent:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }

        // KHUNG danh sách sách đã mượn
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .heightIn(min = 120.dp)
                .background(Color(0xFFF4F4F4))
                .padding(10.dp)
        ) {
            if (borrowedBooks.isEmpty()) {
                Text(
                    "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn {
                    items(borrowedBooks) { book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Checkbox(checked = true, onCheckedChange = {})
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(book)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ô nhập tên sách + nút thêm
        OutlinedTextField(
            value = bookName,
            onValueChange = { bookName = it },
            label = { Text("Tên sách") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (bookName.isNotBlank()) {
                    borrowedBooks.add(bookName)
                    bookName = ""
                    message = "Đã thêm sách vào danh sách!"
                } else {
                    message = "Vui lòng nhập tên sách!"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Thêm")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(message, color = Color(0xFF1565C0), fontSize = 14.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LibraryScreenPreview() {
    QuanLyThuVienTheme {
        LibraryScreen(LibraryManager())
    }
}
