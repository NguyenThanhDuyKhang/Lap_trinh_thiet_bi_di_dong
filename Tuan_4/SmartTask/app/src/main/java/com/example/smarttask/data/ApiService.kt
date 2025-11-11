package com.example.smarttask.data

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Trả về lớp bọc TaskListResponse
    @GET("api/researchUTH/tasks")
    suspend fun getTasks(): TaskListResponse

    // Trả về lớp bọc TaskDetailResponse
    @GET("api/researchUTH/task/{id}")
    suspend fun getTaskDetail(@Path("id") taskId: String): TaskDetailResponse

    // API Xóa giữ nguyên
    @DELETE("api/researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") taskId: String): Response<Unit>
}