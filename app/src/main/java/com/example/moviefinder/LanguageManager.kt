package com.example.moviefinder

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageManager {

    private const val PREF_NAME = "MovieFinderLanguage"
    private const val LANGUAGE_KEY = "selected_language"

    fun saveLanguage(context: Context, languageCode: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(LANGUAGE_KEY, languageCode)
            .apply()
    }

    fun getLanguage(context: Context): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(LANGUAGE_KEY, "en") ?: "en"
    }

    fun applyLanguage(context: Context): Context {
        val languageCode = getLanguage(context)
        val locale = Locale.forLanguageTag(languageCode)

        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }
}
