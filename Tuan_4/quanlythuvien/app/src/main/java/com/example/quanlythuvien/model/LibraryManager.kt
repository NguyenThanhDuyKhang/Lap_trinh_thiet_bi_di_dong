package com.example.quanlythuvien.model

import com.example.quanlythuvien.data.Book
import com.example.quanlythuvien.data.Student

class LibraryManager {
    private val books = mutableListOf(
        Book(1, "Lập trình Kotlin"),
        Book(2, "Cấu trúc dữ liệu"),
        Book(3, "Cơ sở dữ liệu"),
        Book(4, "Mạng máy tính")
    )

    private val students = mutableListOf(
        Student(1, "Nguyễn Văn A"),
        Student(2, "Trần Thị B"),
        Student(3, "Lê Văn C")
    )

    fun getBooks(): List<Book> = books
    fun getStudents(): List<Student> = students

    fun borrowBook(studentId: Int, bookId: Int): Boolean {
        val student = students.find { it.id == studentId }
        val book = books.find { it.id == bookId }

        return if (student != null && book != null && !book.isBorrowed) {
            book.isBorrowed = true
            student.borrowedBooks.add(book)
            true
        } else false
    }

    fun returnBook(studentId: Int, bookId: Int): Boolean {
        val student = students.find { it.id == studentId }
        val book = books.find { it.id == bookId }

        return if (student != null && book != null && book.isBorrowed) {
            book.isBorrowed = false
            student.borrowedBooks.remove(book)
            true
        } else false
    }
}
