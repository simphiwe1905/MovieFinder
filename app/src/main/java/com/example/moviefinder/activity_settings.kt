package com.example.moviefinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class activity_settings : AppCompatActivity() {

    // Apply the saved language before displaying Settings.
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LanguageManager.applyLanguage(newBase)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Account information
        val usernameText = findViewById<TextView>(
            R.id.txtSettingsUsername
        )

        val emailText = findViewById<TextView>(
            R.id.txtSettingsEmail
        )

        val logoutButton = findViewById<Button>(
            R.id.btnLogout
        )

        // Retrieve saved user information
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

        // Display account information
        usernameText.text = username
        emailText.text = email

        // LANGUAGE SELECTION
        val languageSpinner = findViewById<Spinner>(
            R.id.spinnerLanguage
        )

        // Languages currently available in MovieFinder
        val languageNames = listOf(
            getString(R.string.english),
            getString(R.string.afrikaans)
        )

        val languageCodes = listOf(
            "en",
            "af"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            languageNames
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        languageSpinner.adapter = adapter

        // Retrieve the currently saved language
        var selectedLanguage = LanguageManager.getLanguage(this)

        val savedPosition = languageCodes.indexOf(
            selectedLanguage
        ).coerceAtLeast(0)

        languageSpinner.setSelection(savedPosition, false)

        // Listen for language changes
        languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val newLanguage = languageCodes[position]

                    // Only restart when the language changes
                    if (newLanguage != selectedLanguage) {

                        selectedLanguage = newLanguage

                        // Save the user's language preference
                        LanguageManager.saveLanguage(
                            this@activity_settings,
                            newLanguage
                        )

                        // Restart MovieFinder using the new language
                        val intent = Intent(
                            this@activity_settings,
                            MainActivity::class.java
                        )

                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)
                    }
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                    // No action needed
                }
            }

        // LOGOUT
        logoutButton.setOnClickListener {

            sharedPreferences.edit()
                .putBoolean("isLoggedIn", false)
                .apply()

            val intent = Intent(
                this,
                activity_login::class.java
            )

            startActivity(intent)
            finish()
        }
    }
}
