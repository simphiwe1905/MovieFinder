
package com.example.moviefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class activity_register : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var registerButton: Button
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        // Connect Kotlin variables to the XML elements
        username = findViewById(R.id.edtRegisterUsername)
        email = findViewById(R.id.edtRegisterEmail)
        password = findViewById(R.id.edtRegisterPassword)
        confirmPassword = findViewById(R.id.edtRegisterConfirmPassword)
        registerButton = findViewById(R.id.btnRegister)
        loginLink = findViewById(R.id.goToLogin)

        // Register button
        registerButton.setOnClickListener {

            val usernameText = username.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString()
            val confirmPasswordText = confirmPassword.text.toString()

            // Check username
            if (usernameText.isEmpty()) {
                username.error = "Username is required"
                return@setOnClickListener
            }

            // Check email
            if (emailText.isEmpty()) {
                email.error = "Email is required"
                return@setOnClickListener
            }

            // Check if email is valid
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                email.error = "Please enter a valid email"
                return@setOnClickListener
            }

            // Check password
            if (passwordText.isEmpty()) {
                password.error = "Password is required"
                return@setOnClickListener
            }

            // Check confirmation
            if (confirmPasswordText.isEmpty()) {
                confirmPassword.error = "Please confirm your password"
                return@setOnClickListener
            }

            // Check passwords match
            if (passwordText != confirmPasswordText) {
                confirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Save the user's registration information
            val sharedPreferences = getSharedPreferences(
                "MovieFinderUsers",
                MODE_PRIVATE
            )

            sharedPreferences.edit()
                .putString("username", usernameText)
                .putString("email", emailText)
                .putString("password", passwordText)
                .apply()

            // Show success message
            Toast.makeText(
                this,
                "Registration successful!",
                Toast.LENGTH_SHORT
            ).show()

            // Go to Login screen
            val intent = Intent(this, activity_login::class.java)
            startActivity(intent)

            // Close registration screen
            finish()
        }

        // Login link
        loginLink.setOnClickListener {
            finish()
        }
    }
}

