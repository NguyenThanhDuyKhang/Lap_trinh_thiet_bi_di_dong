package com.example.baitap3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.baitap3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()

            when {
                email.isEmpty() -> {
                    binding.tvError.text = "Email không hợp lệ"
                    binding.tvError.visibility = View.VISIBLE
                }
                !email.contains("@") -> {
                    binding.tvError.text = "Email không đúng định dạng"
                    binding.tvError.visibility = View.VISIBLE
                }
                else -> {
                    binding.tvError.text = "Bạn đã nhập email hợp lệ"
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.setTextColor(getColor(android.R.color.holo_green_dark))
                }
            }
        }
    }
}
