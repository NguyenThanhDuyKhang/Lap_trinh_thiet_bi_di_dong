package com.example.quanlythuvien.model

import com.example.quanlythuvien.data.Book
import com.example.quanlythuvien.data.Student

class LibraryManager {
    private val books = mutableListOf(
        Book(1, "Sach 1"),
        Book(2, "Sach 2"),
        Book(3, "Sach 3"),
        Book(4, "Sach 4")
    )

    private val students = mutableListOf(
        Student(1, "Nguyen Van A"),
        Student(2, "Tran Thi B"),
        Student(3, "Le Van C")
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
