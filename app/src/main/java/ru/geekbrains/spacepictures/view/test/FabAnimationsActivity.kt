package ru.geekbrains.spacepictures.view.test

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import ru.geekbrains.spacepictures.databinding.ActivityAnimationsFabBinding

private lateinit var binding: ActivityAnimationsFabBinding

class FabAnimationsActivity : AppCompatActivity() {

    private var textIsVisible = false
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsFabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFAB()
    }

    private fun setFAB() {
        setInitialState()
        binding.fab.setOnClickListener {
            if (isExpanded) {
                collapseFab()
            } else {
                expandFAB()
            }
        }
    }

    private fun setInitialState() {
        binding.transparentBackground.apply {
            alpha = 0f
        }
        binding.optionTwoContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionOneContainer.apply {
            alpha = 0f
            isClickable = false
        }
    }

    private fun expandFAB() {
        isExpanded = true
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, 225f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY",-130f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY",    -250f        ).start()
        binding.optionOneContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = true
                    binding.optionOneContainer.setOnClickListener {
                        Toast.makeText(
                            this@FabAnimationsActivity, "Option 2",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        binding.optionTwoContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = true
                    binding.optionTwoContainer.setOnClickListener {
                        Toast.makeText(
                            this@FabAnimationsActivity, "Option 1",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        binding.transparentBackground.animate()
            .alpha(0.9f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = true
                }
            })
    }

    private fun collapseFab() {
        isExpanded = false
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, -180f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", 0f).start()
        binding.optionOneContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = false
                    binding.optionOneContainer.setOnClickListener(null)
                }
            })
        binding.optionTwoContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = false
                }
            })
        binding.transparentBackground.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = false
                }
            })
    }
}
