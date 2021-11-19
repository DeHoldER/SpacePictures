package ru.geekbrains.spacepictures.view.api.MRP

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.geekbrains.spacepictures.view.api.ApiActivity
import ru.geekbrains.spacepictures.view.api.EarthFragment
import ru.geekbrains.spacepictures.view.api.MarsFragment
import ru.geekbrains.spacepictures.view.api.POD.PictureOfTheDayFragment
import ru.geekbrains.spacepictures.view.api.WeatherFragment


class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        MRPPhotoFragment.newInstance(0),
        MRPPhotoFragment.newInstance(1),
        MRPPhotoFragment.newInstance(2),

    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}