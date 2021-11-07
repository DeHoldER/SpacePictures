package ru.geekbrains.spacepictures.view

import android.os.Bundle
import android.view.View
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.FragmentSettingsBinding
import ru.geekbrains.spacepictures.util.ThemeService
import ru.geekbrains.spacepictures.util.ViewBindingFragment
import android.content.Intent
import ru.geekbrains.spacepictures.view.test.AnimationsActivity


class SettingsFragment :
    ViewBindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onStart() {
        super.onStart()
        checkTheme()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnThemeDefault.setOnClickListener {
            ThemeService.updateTheme(ThemeService.THEME_INDIGO)
        }
        binding.btnThemePink.setOnClickListener {
            ThemeService.updateTheme(ThemeService.THEME_PINK)
        }
        binding.btnThemeMars.setOnClickListener {
            ThemeService.updateTheme(ThemeService.THEME_MARS)
        }
        binding.btnThemeJupiter.setOnClickListener {
            ThemeService.updateTheme(ThemeService.THEME_JUPITER)
        }
        binding.btnShowAnimations.setOnClickListener {
            val intent = Intent(requireContext(), AnimationsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkTheme() {
        when (ThemeService.getCurrentTheme()) {
            R.style.AppTheme_Indigo -> binding.btnThemeDefault.isChecked = true
            R.style.AppTheme_Pink -> binding.btnThemePink.isChecked = true
            R.style.AppTheme_Mars -> binding.btnThemeMars.isChecked = true
            R.style.AppTheme_Jupiter -> binding.btnThemeJupiter.isChecked = true
        }
    }
}