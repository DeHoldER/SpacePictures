package ru.geekbrains.spacepictures.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.geekbrains.spacepictures.view.api.POD.PictureOfTheDayFragment


class ViewPagerAdapter(private val fragmentManager: FragmentManager, private val activity: ApiActivity) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        EarthFragment(),
        MarsFragment(),
        WeatherFragment(),
        PictureOfTheDayFragment(),
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return when (position) {
//            0 -> activity.getString(R.string.txt_earth)
//            1 -> activity.getString(R.string.txt_mars)
//            2 -> activity.getString(R.string.txt_weather)
//            else -> activity.getString(R.string.txt_earth)
//        }
//    }

}