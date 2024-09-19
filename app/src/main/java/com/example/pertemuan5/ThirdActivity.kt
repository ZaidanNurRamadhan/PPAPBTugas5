package com.example.pertemuan5

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.annotation.SuppressLint
import androidx.activity.enableEdgeToEdge

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val tvGender = findViewById<TextView>(R.id.tvGender)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)

        // Ambil data dari SharedPreferences
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val gender = sharedPreferences.getString("gender","")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")

        // Tampilkan data
        tvWelcome.text = "Username: $username"
        tvGender.text = "Gender: $gender"
        tvEmail.text = "Email $email"
        tvPhone.text = "NumPhone: $phone"
        }
    }