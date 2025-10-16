package com.example.btvn_kttuoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.btvn_kttuoi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKiemTra.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val ageText = binding.edtAge.text.toString().trim()

            if (name.isEmpty()) {
                binding.tvResult.text = "Vui lòng nhập họ tên"
                binding.tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                return@setOnClickListener
            }

            if (ageText.isEmpty()) {
                binding.tvResult.text = "Vui lòng nhập tuổi"
                binding.tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                return@setOnClickListener
            }

            val age = ageText.toIntOrNull()
            if (age == null) {
                binding.tvResult.text = "Tuổi không hợp lệ"
                binding.tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                return@setOnClickListener
            }

            val loaiNguoi = when {
                age > 65 -> "Người già"
                age >= 6 -> "Người lớn"
                age >= 2 -> "Trẻ em"
                else -> "Em bé"
            }

            binding.tvResult.text = "$name (${age} tuổi) là $loaiNguoi"
            binding.tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        }
    }
}
