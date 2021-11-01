package ru.geekbrains.spacepictures.util

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.view.MainActivity
import ru.geekbrains.spacepictures.view.test.TestActivity

object ThemeService {
    const val THEME_INDIGO = 0
    const val THEME_PINK = 1
    const val THEME_MARS = 2
    const val THEME_JUPITER = 3
    private const val SHARED_PREF_KEY = "themeNumber"

    private lateinit var contextActivity: MainActivity
    private lateinit var sharedPref: SharedPreferences

    private var currentTheme = R.style.AppTheme_Indigo

    private val themesList = arrayOf(
        R.style.AppTheme_Indigo,
        R.style.AppTheme_Pink,
        R.style.AppTheme_Mars,
        R.style.AppTheme_Jupiter,
    )

    fun getCurrentTheme(): Int {
        return themesList[sharedPref.getInt(SHARED_PREF_KEY, THEME_INDIGO)]
    }

    fun updateTheme(themeNumber: Int) {
        currentTheme = themesList[themeNumber]
        sharedPref
            .edit()
            .putInt(SHARED_PREF_KEY, themeNumber)
            .apply()
        contextActivity.setTheme(themesList[themeNumber])
        contextActivity.recreate()
    }

    fun registerActivity(activity: MainActivity) {
            contextActivity = activity
            sharedPref = contextActivity.getPreferences(AppCompatActivity.MODE_PRIVATE)
    }

    fun loadTheme() {
        currentTheme = getCurrentTheme()
        contextActivity.setTheme(currentTheme)
    }


}