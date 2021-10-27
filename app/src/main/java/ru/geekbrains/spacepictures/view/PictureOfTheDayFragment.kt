package ru.geekbrains.spacepictures.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.geekbrains.spacepictures.model.repository.POD.PictureOfTheDayData
import ru.geekbrains.spacepictures.PictureOfTheDayViewModel
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.FragmentPictureOfTheDayBinding
import ru.geekbrains.spacepictures.util.WIKI_BASE_URL_RU

class PictureOfTheDayFragment : ViewBindingFragment<FragmentPictureOfTheDayBinding>(FragmentPictureOfTheDayBinding::inflate) {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        //TODO показать подпись к фото

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("$WIKI_BASE_URL_RU${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
//Отобразите ошибку
//showError("Сообщение, что ссылка пустая")
                } else {
                    val header = view?.findViewById<TextView>(R.id.bottom_sheet_description_header)
                    val description = view?.findViewById<TextView>(R.id.bottom_sheet_description)

                    header?.text = serverResponseData.title
                    description?.text = serverResponseData.explanation
//Отобразите фото
//showSuccess()
//Coil в работе: достаточно вызвать у нашего ImageView
//нужную extension-функцию и передать ссылку и заглушки для placeholder
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_no_photo_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
//Отобразите загрузку
//showLoading()
            }
            is PictureOfTheDayData.Error -> {
            }
        }
    }

}
