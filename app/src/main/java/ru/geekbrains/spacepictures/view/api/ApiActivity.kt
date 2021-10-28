package ru.geekbrains.spacepictures.view.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.ActivityApiBinding
import ru.geekbrains.spacepictures.databinding.ActivityMainBinding

private lateinit var binding: ActivityApiBinding

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val activity = this

        with(binding) {
            viewPager.adapter = ViewPagerAdapter(supportFragmentManager, activity)
            tabLayout.setupWithViewPager(viewPager)
            tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_earth)
            tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_mars)
            tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_system)
        }


    }
}