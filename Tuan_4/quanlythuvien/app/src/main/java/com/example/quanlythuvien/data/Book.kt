package com.example.quanlythuvien.data

data class Book(
    val id: Int,
    val title: String,
    var isBorrowed: Boolean=false
)
