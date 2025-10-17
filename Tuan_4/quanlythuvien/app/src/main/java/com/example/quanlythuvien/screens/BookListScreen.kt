package com.example.quanlythuvien.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quanlythuvien.model.LibraryManager

@Composable
fun BookListScreen(manager: LibraryManager) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Danh sách Sách", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(manager.getBooks()) { book ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(
                        "${book.title} ${if (book.isBorrowed) "(Đã mượn)" else "(Còn trống)"}",
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}
