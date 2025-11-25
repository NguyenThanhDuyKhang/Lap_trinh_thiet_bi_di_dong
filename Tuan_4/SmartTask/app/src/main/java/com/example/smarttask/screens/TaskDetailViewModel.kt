package com.example.smarttask.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttask.data.RetrofitInstance
import com.example.smarttask.data.TaskDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Trạng thái cho màn hình chi tiết (Giữ nguyên)
sealed interface TaskDetailUiState {
    object Loading : TaskDetailUiState
    data class Success(val task: TaskDetail) : TaskDetailUiState
    data class Error(val message: String) : TaskDetailUiState
    object Deleted : TaskDetailUiState
}

class TaskDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val uiState: StateFlow<TaskDetailUiState> = _uiState

    // Sửa logic hàm loadTaskDetail
    fun loadTaskDetail(taskId: String) {
        viewModelScope.launch {
            _uiState.value = TaskDetailUiState.Loading
            try {
                // 1. Gọi API, trả về TaskDetailResponse
                val response = RetrofitInstance.api.getTaskDetail(taskId)

                // 2. Lấy task từ trường "data"
                val taskDetail = response.data

                // 3. Kiểm tra isSuccess và data có null không
                if (response.isSuccess && taskDetail != null) {
                    _uiState.value = TaskDetailUiState.Success(taskDetail)
                } else {
                    _uiState.value = TaskDetailUiState.Error(response.message)
                }
            } catch (e: Exception) {
                _uiState.value = TaskDetailUiState.Error(e.message ?: "Lỗi không xác định")
            }
        }
    }

    // Hàm deleteTask giữ nguyên
    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            _uiState.value = TaskDetailUiState.Loading
            try {
                RetrofitInstance.api.deleteTask(taskId)
                _uiState.value = TaskDetailUiState.Deleted
            } catch (e: Exception) {
                _uiState.value = TaskDetailUiState.Error("Xóa thất bại: ${e.message}")
            }
        }
    }
}