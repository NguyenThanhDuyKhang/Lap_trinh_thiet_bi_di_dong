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
fun StudentListScreen(manager: LibraryManager){
    Column(modifier = Modifier.padding(16.dp)){
        Text("Danh sach sinh vien", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(  manager.getStudents()){ student ->
                Card (Modifier.fillMaxWidth().padding(vertical = 4.dp)){
                    Column(Modifier.padding(12.dp)){
                        Text("Ten: ${student.name}")
                        if (student.borrowedBooks.isEmpty())
                            Text("Chua muon sach nao")
                        else
                            Text("Dang muon: ${student.borrowedBooks.joinToString {it.title  }}")
                    }
                }
            }
        }
    }
}