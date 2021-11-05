package ru.geekbrains.spacepictures.view.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.ActivityApiBinding

private lateinit var binding: ActivityApiBinding

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activity = this
            binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, activity)
//        with(binding) {
//            viewPager.adapter = ViewPagerAdapter(supportFragmentManager, activity)
//            tabLayout.setupWithViewPager(viewPager)
//            tabLayout.getTabAt(EARTH_FRAGMENT)?.setIcon(R.drawable.ic_earth)
//            tabLayout.getTabAt(MARS_FRAGMENT)?.setIcon(R.drawable.ic_mars)
//            tabLayout.getTabAt(WEATHER_FRAGMENT)?.setIcon(R.drawable.ic_system)
//            tabLayout.getTabAt(POD_FRAGMENT)?.setIcon(R.drawable.ic_telescope)
//        }


    }
}