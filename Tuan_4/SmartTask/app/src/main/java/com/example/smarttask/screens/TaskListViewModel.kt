package com.example.smarttask.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttask.data.RetrofitInstance
import com.example.smarttask.data.TaskDetail // Sửa thành TaskDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// UiState giờ sẽ chứa danh sách List<TaskDetail>
sealed interface TaskListUiState {
    object Loading : TaskListUiState
    data class Success(val tasks: List<TaskDetail>) : TaskListUiState // Sửa ở đây
    object Empty : TaskListUiState
    data class Error(val message: String) : TaskListUiState
}

class TaskListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState> = _uiState

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = TaskListUiState.Loading
            try {
                // 1. Gọi API
                val response = RetrofitInstance.api.getTasks() // Đây là TaskListResponse

                // 2. Lấy danh sách task từ trường "data"
                val tasks = response.data

                // 3. Kiểm tra isSuccess và danh sách có null/rỗng không
                if (response.isSuccess && !tasks.isNullOrEmpty()) {
                    _uiState.value = TaskListUiState.Success(tasks)
                } else {
                    _uiState.value = TaskListUiState.Empty // Vẫn thành công nhưng rỗng
                }
            } catch (e: Exception) {
                _uiState.value = TaskListUiState.Error(e.message ?: "Lỗi không xác định")
            }
        }
    }
}