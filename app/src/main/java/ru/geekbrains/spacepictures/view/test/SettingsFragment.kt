package ru.geekbrains.spacepictures.view.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import com.google.android.material.chip.Chip
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.FragmentChipsBinding
import ru.geekbrains.spacepictures.databinding.FragmentSettingsBinding
import ru.geekbrains.spacepictures.view.MainActivity
import ru.geekbrains.spacepictures.view.ViewBindingFragment

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
            MainActivity.ThemeHolder.theme = R.style.AppTheme_IndigoTheme
            recreate(requireActivity())
        }
        binding.btnThemePink.setOnClickListener {
            MainActivity.ThemeHolder.theme = R.style.AppTheme_PinkTheme
            recreate(requireActivity())
        }
    }

    private fun checkTheme() {
        when (MainActivity.ThemeHolder.theme) {
            R.style.AppTheme_IndigoTheme -> binding.btnThemeDefault.isChecked = true
            R.style.AppTheme_PinkTheme -> binding.btnThemePink.isChecked = true
        }
    }
}