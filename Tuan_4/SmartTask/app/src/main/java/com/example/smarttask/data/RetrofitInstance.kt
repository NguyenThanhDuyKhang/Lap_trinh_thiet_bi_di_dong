package com.example.smarttask.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://amock.io/"

    // Tạo một logger để xem các cuộc gọi API trong Logcat (Rất hữu ích khi debug)
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Tạo đối tượng Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient) // Sử dụng httpClient đã có logger
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Tạo một instance của ApiService để các ViewModel có thể sử dụng
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}