package com.example.pertemuan5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<TextInputEditText>(R.id.username)
        val email = findViewById<TextInputEditText>(R.id.email)
        val phone = findViewById<TextInputEditText>(R.id.phone)
        val password = findViewById<TextInputEditText>(R.id.password)
        val gender = findViewById<TextInputEditText>(R.id.gender)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginLink = findViewById<TextView>(R.id.loginLink)

        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)

        // Add TextWatcher for gender validation
        gender.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().trim()
                if (isValidGender(input)) {
                    gender.error = null
                } else {
                    gender.error = "Please enter 'Male' or 'Female'."
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        registerButton.setOnClickListener {
            val usernameText = username.text.toString().trim()
            val emailText = email.text.toString().trim()
            val phoneText = phone.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val genderText = gender.text.toString().trim()

            if (usernameText.isEmpty() || emailText.isEmpty() || phoneText.isEmpty() || passwordText.isEmpty() || genderText.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate gender
            if (!isValidGender(genderText)) {
                Toast.makeText(this, "Please enter 'Male' or 'Female'.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save data to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("username", usernameText)
            editor.putString("email", emailText)
            editor.putString("phone", phoneText)
            editor.putString("password", passwordText)
            editor.putString("gender", genderText) // Save gender as well
            editor.apply()

            // Move to Login page
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Move to Login page directly
        loginLink.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to validate gender
    private fun isValidGender(input: String): Boolean {
        return input.equals("Male", ignoreCase = true) || input.equals("Female", ignoreCase = true)
    }
}