
package com.example.moviefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class activity_settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connect this Kotlin file to the settings XML layout
        setContentView(R.layout.activity_settings)

        // Connect XML elements to Kotlin
        val usernameText = findViewById<TextView>(
            R.id.txtSettingsUsername
        )

        val emailText = findViewById<TextView>(
            R.id.txtSettingsEmail
        )

        val logoutButton = findViewById<Button>(
            R.id.btnLogout
        )

        // Get the saved user information
        val sharedPreferences = getSharedPreferences(
            "MovieFinderUsers",
            MODE_PRIVATE
        )

        val username = sharedPreferences.getString(
            "username",
            "Username not found"
        )

        val email = sharedPreferences.getString(
            "email",
            "Email not found"
        )

        // Display the user's information
        usernameText.text = "Username: $username"
        emailText.text = "Email: $email"

        // Logout button
        logoutButton.setOnClickListener {

            // Change the login status to false
            sharedPreferences.edit()
                .putBoolean("isLoggedIn", false)
                .apply()

            // Send the user back to the Login screen
            val intent = Intent(
                this,
                activity_login::class.java
            )

            startActivity(intent)

            // Close the Settings screen
            finish()
        }
    }
}
