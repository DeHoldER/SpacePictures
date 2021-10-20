package ru.geekbrains.spacepictures.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.geekbrains.spacepictures.model.repository.POD.PictureOfTheDayData
import ru.geekbrains.spacepictures.PictureOfTheDayViewModel
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.FragmentMainBinding

class PictureOfTheDayFragment : Fragment() {

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })
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
//Отобразите фото
//showSuccess()
//Coil в работе: достаточно вызвать у нашего ImageView
//нужную extension-функцию и передать ссылку и заглушки для placeholder
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_twotone_no_photography_24)
                        placeholder(R.drawable.ic_twotone_no_photography_24)
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
