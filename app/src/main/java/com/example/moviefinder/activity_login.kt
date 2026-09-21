
package com.example.moviefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class activity_login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Connect XML elements to Kotlin
        val email = findViewById<EditText>(R.id.edtLoginEmail)
        val password = findViewById<EditText>(R.id.edtLoginPassword)

        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerButton = findViewById<Button>(R.id.btnGoToRegister)

        // LOGIN button
        loginButton.setOnClickListener {

            val enteredEmail = email.text.toString().trim()
            val enteredPassword = password.text.toString()

            // Check if email is empty
            if (enteredEmail.isEmpty()) {
                email.error = "Please enter your email"
                return@setOnClickListener
            }

            // Check if password is empty
            if (enteredPassword.isEmpty()) {
                password.error = "Please enter your password"
                return@setOnClickListener
            }

            // Get the saved registration information
            val sharedPreferences = getSharedPreferences(
                "MovieFinderUsers",
                MODE_PRIVATE
            )

            val savedEmail = sharedPreferences.getString("email", null)
            val savedPassword = sharedPreferences.getString("password", null)

            // Check if an account exists
            if (savedEmail == null || savedPassword == null) {

                Toast.makeText(
                    this,
                    "No account found. Please register first.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            // Check login details
            if (enteredEmail == savedEmail && enteredPassword == savedPassword) {

                // Save login session
                sharedPreferences.edit()
                    .putBoolean("isLoggedIn", true)
                    .apply()

                // Login successful
                Toast.makeText(
                    this,
                    "Login successful!",
                    Toast.LENGTH_SHORT
                ).show()

                // Open the movie screen
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Prevent going back to login
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Incorrect email or password",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // CREATE ACCOUNT button
            registerButton.setOnClickListener {

                val intent = Intent(
                    this,
                    activity_register::class.java
                )

                startActivity(intent)
            }
        }
    }
}
