package ru.geekbrains.spacepictures.view.api.MRP

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.FragmentMrpMainBinding
import ru.geekbrains.spacepictures.model.repository.MRP.MRPData
import ru.geekbrains.spacepictures.util.ViewBindingFragment
import ru.geekbrains.spacepictures.viewmodel.MRPViewModel

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
        viewModel
            .getData()
            .observe(viewLifecycleOwner, {
                renderData(it)
            })
    }

    private fun renderData(data: MRPData) {
        when (data) {
            is MRPData.Success -> {
                if (!data.serverResponseData.photosArray.isNullOrEmpty()) {
                    val url = data.serverResponseData.photosArray[0].imageSource
                    binding.imageView.load(url) {
                        switchLoadingVisibility(false)
                    }
                }
            }
        }
    }

    private fun switchLoadingVisibility(isVisible: Boolean = true) {
        with(binding) {
            if (isVisible)
                binding.mrpLoading.visibility = View.VISIBLE
            else
                binding.mrpLoading.visibility = View.GONE
        }
    }

}