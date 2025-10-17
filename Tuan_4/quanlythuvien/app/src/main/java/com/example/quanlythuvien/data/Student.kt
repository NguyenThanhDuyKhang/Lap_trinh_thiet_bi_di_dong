package com.example.quanlythuvien.data

data class Student(
    val id:Int,
    val name:String,
    val borrowedBooks : MutableList<Book>  = mutableListOf()
)