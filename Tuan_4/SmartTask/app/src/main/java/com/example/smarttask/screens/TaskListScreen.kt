package com.example.smarttask.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smarttask.data.TaskDetail // Sửa: Dùng TaskDetail
import com.example.smarttask.navigation.AppRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavController,
    taskListViewModel: TaskListViewModel = viewModel()
) {
    val uiState by taskListViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UTH SmartTasks") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Thêm task mới */ }) {
                Text("+") // Icon Thêm
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when (val state = uiState) {
                is TaskListUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is TaskListUiState.Empty -> {
                    EmptyStateView()
                }
                is TaskListUiState.Success -> {
                    // Sửa: Truyền List<TaskDetail>
                    TasksListView(tasks = state.tasks, navController = navController)
                }
                is TaskListUiState.Error -> {
                    Text(
                        text = "Lỗi: ${state.message}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

// Sửa: Nhận vào List<TaskDetail>
@Composable
fun TasksListView(tasks: List<TaskDetail>, navController: NavController) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(tasks) { task ->
            TaskItem(task = task, onClick = {
                // Sửa: Đổi task.id (Int) thành String khi điều hướng
                navController.navigate(AppRoutes.TASK_DETAIL.replace("{taskId}", task.id.toString()))
            })
        }
    }
}

// Sửa: Nhận vào 1 TaskDetail
@Composable
fun TaskItem(task: TaskDetail, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            // Hiển thị thêm Priority cho giống JSON
            Text("Status: ${task.status}", style = MaterialTheme.typography.bodySmall)
            Text("Priority: ${task.priority}", style = MaterialTheme.typography.bodySmall)
            Text("Due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

// Giữ nguyên EmptyStateView
@Composable
fun EmptyStateView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Tasks Yet!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Stay productive—add something to do.",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}