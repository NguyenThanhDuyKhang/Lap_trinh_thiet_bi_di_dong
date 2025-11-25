package com.example.smarttask.data

import com.google.gson.annotations.SerializedName

// LỚP BỌC CHO API LẤY DANH SÁCH TASK (GET /tasks)
data class TaskListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<TaskDetail>? // Danh sách task nằm trong "data"
)

// LỚP BỌC CHO API LẤY CHI TIẾT (GET /task/{id})
data class TaskDetailResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: TaskDetail? // Chỉ 1 task nằm trong "data"
)

// LỚP DỮ LIỆU CHÍNH CHO 1 TASK
// Dùng cho cả danh sách và chi tiết, vì API trả về đầy đủ thông tin
data class TaskDetail(
    @SerializedName("id") val id: Int, // id là Int
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("status") val status: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("dueDate") val dueDate: String,
    @SerializedName("subtasks") val subtasks: List<Subtask>?, // Có thể null
    @SerializedName("attachments") val attachments: List<Attachment>? // Có thể null
)

// LỚP CHO SUBTASK (Sửa "title" cho khớp JSON)
data class Subtask(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String, // JSON dùng "title"
    @SerializedName("isCompleted") val isCompleted: Boolean
)

// LỚP CHO ATTACHMENT (Sửa "fileUrl" cho khớp JSON)
data class Attachment(
    @SerializedName("id") val id: Int,
    @SerializedName("fileName") val fileName: String,
    @SerializedName("fileUrl") val fileUrl: String // JSON dùng "fileUrl"
)