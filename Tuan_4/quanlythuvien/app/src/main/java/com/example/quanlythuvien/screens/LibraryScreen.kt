package com.example.quanlythuvien.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quanlythuvien.model.LibraryManager
import com.example.quanlythuvien.ui.theme.QuanLyThuVienTheme
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun LibraryScreen(manager: LibraryManager) {
    var selectedStudentId by remember { mutableStateOf(1) }
    var selectedBookId by remember { mutableStateOf(1) }
    var message by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp)) {
        Text("Quản lý thư viện", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        Text("Chọn sinh viên:")
        DropdownMenuBox(
            items = manager.getStudents().map { it.name },
            selectedIndex = selectedStudentId - 1,
            onItemSelected = { selectedStudentId = it + 1 }
        )

        Spacer(Modifier.height(12.dp))
        Text("Chọn sách:")
        DropdownMenuBox(
            items = manager.getBooks().map { it.title },
            selectedIndex = selectedBookId - 1,
            onItemSelected = { selectedBookId = it + 1 }
        )

        Spacer(Modifier.height(20.dp))
        Row {
            Button(onClick = {
                message = if (manager.borrowBook(selectedStudentId, selectedBookId))
                    "Mượn thành công!"
                else "Không thể mượn sách!"
            }) {
                Text("Mượn")
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = {
                message = if (manager.returnBook(selectedStudentId, selectedBookId))
                    "Trả thành công!"
                else "Không thể trả sách!"
            }) {
                Text("Trả")
            }
        }

        Spacer(Modifier.height(16.dp))
        Text(message, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun DropdownMenuBox(
    items: List<String>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(items[selectedIndex])
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEachIndexed { index, label ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        onItemSelected(index)
                        expanded = false
                    }
                )
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true, name = "Library Screen")
@Composable
fun LibraryScreenPreview() {
    QuanLyThuVienTheme {
        val manager = LibraryManager()
        LibraryScreen(manager)
    }
}
