package com.example.baitap21

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var etNumber: EditText
    private lateinit var btnCreate: Button
    private lateinit var tvError: TextView
    private lateinit var rvNumbers: RecyclerView
    private lateinit var adapter: NumberAdapter

    private val MAX_ITEMS = 200  // nếu muốn giới hạn, thay giá trị

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber = findViewById(R.id.etNumber)
        btnCreate = findViewById(R.id.btnCreate)
        tvError = findViewById(R.id.tvError)
        rvNumbers = findViewById(R.id.rvNumbers)

        adapter = NumberAdapter()
        rvNumbers.layoutManager = LinearLayoutManager(this)
        rvNumbers.adapter = adapter

        btnCreate.setOnClickListener {
            val text = etNumber.text?.toString()?.trim()
            val n = text?.toIntOrNull()

            if (n == null || n <= 0) {
                // KHÔNG PHẢI SỐ => show lỗi và xóa danh sách (nếu có)
                tvError.visibility = View.VISIBLE
                tvError.text = "Dữ liệu bạn nhập không hợp lệ"
                adapter.updateList(emptyList())
            } else {
                // SỐ HỢP LỆ => ẩn lỗi, tạo danh sách 1..n (giới hạn nếu cần)
                tvError.visibility = View.GONE
                val finalN = if (n > MAX_ITEMS) {
                    Toast.makeText(this, "Giới hạn tối đa $MAX_ITEMS mục", Toast.LENGTH_SHORT).show()
                    MAX_ITEMS
                } else n
                val list = (1..finalN).toList()
                adapter.updateList(list)
                // cuộn lên đầu nếu muốn
                if (list.isNotEmpty()) rvNumbers.scrollToPosition(0)
            }
        }
    }
}
