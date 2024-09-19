package com.example.pertemuan5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class SecondActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val LoginUsername = findViewById<TextInputEditText>(R.id.username)
        val LoginPassword = findViewById<TextInputEditText>(R.id.password)
        val btnLogin = findViewById<Button>(R.id.loginButton)
        val txtRegister = findViewById<TextView>(R.id.registerLink)

        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val username = LoginUsername.text.toString()
            val password = LoginPassword.text.toString()

            // Cek data dari SharedPreferences
            val storedUsername = sharedPreferences.getString("username", "")
            val storedPassword = sharedPreferences.getString("password", "")

            if (username == storedUsername && password == storedPassword) {
                // Pindah ke Homepage
                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
            } else {
                // Tampilkan pesan kesalahan
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }

        txtRegister.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}