package com.example.smarttask.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smarttask.data.TaskDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    navController: NavController,
    taskId: String,
    detailViewModel: TaskDetailViewModel = viewModel()
) {
    val uiState by detailViewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Gọi API khi màn hình được mở
    LaunchedEffect(key1 = taskId) {
        detailViewModel.loadTaskDetail(taskId)
    }

    // Xử lý khi xóa thành công
    LaunchedEffect(key1 = uiState) {
        if (uiState is TaskDetailUiState.Deleted) {
            Toast.makeText(context, "Đã xóa task!", Toast.LENGTH_SHORT).show()
            navController.popBackStack() // Quay lại
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        detailViewModel.deleteTask(taskId)
                    }) {
                        Icon(Icons.Default.Delete, "Delete", tint = Color.Red)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when (val state = uiState) {
                is TaskDetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is TaskDetailUiState.Success -> {
                    TaskDetailContent(task = state.task)
                }
                is TaskDetailUiState.Error -> {
                    Text(
                        text = "Lỗi: ${state.message}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is TaskDetailUiState.Deleted -> {
                    // Đang xóa, hiển thị loading
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun TaskDetailContent(task: TaskDetail) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Text(task.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(task.description)
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChipView("Category: ${task.category}")
                ChipView("Status: ${task.status}")
                ChipView("Priority: ${task.priority}")
            }
        }

        // Sửa Subtasks (thêm ? và dùng .title)
        if (!task.subtasks.isNullOrEmpty()) {
            item {
                Text("Subtasks", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
            items(task.subtasks) { subtask ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = subtask.isCompleted, onCheckedChange = null)
                    Text(subtask.title) // Sửa: Dùng .title
                }
            }
        }

        // Sửa Attachments (thêm ?)
        if (!task.attachments.isNullOrEmpty()) {
            item {
                Text("Attachments", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
            items(task.attachments) { attachment ->
                Text(attachment.fileName)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipView(text: String) {
    SuggestionChip(
        onClick = { },
        label = { Text(text) }
    )
}