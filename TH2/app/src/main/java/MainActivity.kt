package com.example.th2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val tvThongBao = findViewById<TextView>(R.id.tvThongBao)
        val btnKiemTra = findViewById<Button>(R.id.btnKiemTra)


        btnKiemTra.setOnClickListener {
            val email = edtEmail.text.toString().trim()

            when {
                email.isEmpty() -> {
                    tvThongBao.text = "Email không hợp lệ"
                    tvThongBao.setTextColor(getColor(android.R.color.holo_red_dark))
                }
                !email.contains("@") -> {
                    tvThongBao.text = "Email không đúng định dạng"
                    tvThongBao.setTextColor(getColor(android.R.color.holo_red_dark))
                }
                else -> {
                    tvThongBao.text = "Bạn đã nhập email hợp lệ"
                    tvThongBao.setTextColor(getColor(android.R.color.holo_green_dark))
                }
            }
        }
    }
}
