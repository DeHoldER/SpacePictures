package ru.geekbrains.spacepictures.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.util.ThemeService
import ru.geekbrains.spacepictures.view.test.BottomBarTestFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ThemeService.registerActivity(this)
        ThemeService.loadTheme()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BottomBarTestFragment.newInstance())
            .addToBackStack(null)
            .commit()
    } //
}