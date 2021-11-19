package ru.geekbrains.spacepictures.view

import FragmentSplashScreen
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.ActivityMainBinding
import ru.geekbrains.spacepictures.util.ThemeService
import ru.geekbrains.spacepictures.view.api.MRP.MRPMainFragment
import ru.geekbrains.spacepictures.view.api.POD.PictureOfTheDayFragment
import ru.geekbrains.spacepictures.view.test.notes.NotesViewModel

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val notesViewModel: NotesViewModel by lazy {
        ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ThemeService.registerActivity(this)
        ThemeService.loadTheme()

        initBottomNavigation()

        showFragment(FragmentSplashScreen())
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_pod -> {
                    showFragment(PictureOfTheDayFragment.newInstance())
                    true
                }
                R.id.bottom_view_earth -> {

                    true
                }
                R.id.bottom_view_mars -> {
                    showFragment(MRPMainFragment.newInstance())
                    true
                }
                R.id.bottom_view_weather -> {

                    true
                }
                R.id.bottom_view_more -> {
                    showFragment(SettingsFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun getViewModel(): NotesViewModel {
        return notesViewModel
    }
}