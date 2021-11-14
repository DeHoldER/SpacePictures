package ru.geekbrains.spacepictures.view.api.MRP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.geekbrains.spacepictures.databinding.FragmentMrpMainBinding
import ru.geekbrains.spacepictures.model.repository.MRP.MRPData
import ru.geekbrains.spacepictures.util.ViewBindingFragment
import ru.geekbrains.spacepictures.viewmodel.MRPViewModel
import kotlin.random.Random
import kotlin.random.nextInt

class MRPMainFragment :
    ViewBindingFragment<FragmentMrpMainBinding>(FragmentMrpMainBinding::inflate) {

    private val viewModel: MRPViewModel by lazy {
        ViewModelProvider(this).get(MRPViewModel::class.java)
    }

    companion object {
        fun newInstance() = MRPMainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel
//            .getData()
//            .observe(viewLifecycleOwner, {
//                renderData(it)
//            })
//    }

//    private fun renderData(data: MRPData) {
//        when (data) {
//            is MRPData.Success -> {
//                if (!data.serverResponseData.photosArray.isNullOrEmpty()) {
//                val photosArray = data.serverResponseData.photosArray
//                    val url = photosArray[Random.nextInt(photosArray.indices)].imageSource
//                    ru.geekbrains.spacepictures.view.test.binding.imageView.load(url) {
//                        switchLoadingVisibility(false)
//                    }
//                }
//            }
//            else -> switchLoadingVisibility()
//        }
//    }

//    private fun switchLoadingVisibility(isVisible: Boolean = true) {
//        with(ru.geekbrains.spacepictures.view.test.binding) {
//            if (isVisible)
//                ru.geekbrains.spacepictures.view.test.binding.mrpLoading.visibility = View.VISIBLE
//            else
//                ru.geekbrains.spacepictures.view.test.binding.mrpLoading.visibility = View.GONE
//        }
//    }

}