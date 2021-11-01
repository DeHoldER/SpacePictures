package ru.geekbrains.spacepictures.view.test

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.chip.Chip
import ru.geekbrains.spacepictures.databinding.FragmentChipsBinding
import ru.geekbrains.spacepictures.util.ViewBindingFragment

class ChipsFragment :
    ViewBindingFragment<FragmentChipsBinding>(FragmentChipsBinding::inflate) {

    companion object {
        fun newInstance() = ChipsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                Toast.makeText(
                    context, "Выбран ${it.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.chipClose.setOnCloseIconClickListener {
            Toast.makeText(
                context,
                "Close is Clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}