package ru.geekbrains.spacepictures.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.geekbrains.spacepictures.R
import kotlin.coroutines.coroutineContext

private const val EARTH_FRAGMENT = 0
private const val MARS_FRAGMENT = 1
private const val WEATHER_FRAGMENT = 2

class ViewPagerAdapter(private val fragmentManager: FragmentManager, private val activity: ApiActivity) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        EarthFragment(),
        MarsFragment(),
        WeatherFragment()
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[EARTH_FRAGMENT]
            1 -> fragments[MARS_FRAGMENT]
            2 -> fragments[WEATHER_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
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