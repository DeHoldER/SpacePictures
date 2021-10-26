package ru.geekbrains.spacepictures.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StyleRes
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.view.test.BottomBarTestFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTheme(ThemeHolder.theme)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BottomBarTestFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }


    object ThemeHolder {
        @StyleRes
        var theme: Int = R.style.AppTheme_IndigoTheme
    }
}