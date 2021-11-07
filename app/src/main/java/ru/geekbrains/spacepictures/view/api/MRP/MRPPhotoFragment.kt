package ru.geekbrains.spacepictures.view.api.MRP

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import ru.geekbrains.spacepictures.databinding.FragmentMrpphotoBinding
import ru.geekbrains.spacepictures.model.repository.MRP.MRPData
import ru.geekbrains.spacepictures.util.ViewBindingFragment
import ru.geekbrains.spacepictures.viewmodel.MRPViewModel

class MRPPhotoFragment : ViewBindingFragment<FragmentMrpphotoBinding>(
    FragmentMrpphotoBinding::inflate
) {
    private var isPhotoExpanded = false
    private var fragmentNumber: Int? = 0

    private val viewModel: MRPViewModel by lazy {
        ViewModelProvider(this).get(MRPViewModel::class.java)
    }

    companion object {
        fun newInstance(fragmentNumber: Int): MRPPhotoFragment {
            val fragment = MRPPhotoFragment()
            val args = Bundle()
            args.putInt("fragmentNumber", fragmentNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentNumber = arguments?.getInt("fragmentNumber", 0)
    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            imageView.setOnClickListener {
                isPhotoExpanded = !isPhotoExpanded
                TransitionManager.beginDelayedTransition(
                    photoContainer, TransitionSet()
                        .addTransition(ChangeBounds())
                        .addTransition(ChangeImageTransform())
                )
                val params: ViewGroup.LayoutParams = imageView.layoutParams
                params.height =
                    if (isPhotoExpanded) ViewGroup.LayoutParams.MATCH_PARENT else
                        ViewGroup.LayoutParams.WRAP_CONTENT
                imageView.layoutParams = params
                imageView.scaleType =
                    if (isPhotoExpanded) ImageView.ScaleType.CENTER_CROP else
                        ImageView.ScaleType.FIT_CENTER
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            renderData(it)
        })
    }

    private fun renderData(data: MRPData) {
        when (data) {
            is MRPData.Success -> {
                if (!data.serverResponseData.photosArray.isNullOrEmpty()) {
                    val photosArray = data.serverResponseData.photosArray
                    val url = photosArray[fragmentNumber ?: 0].imageSource
                    binding.imageView.load(url) {
                        switchLoadingVisibility(false)
                    }
                }
            }
            else -> switchLoadingVisibility()
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