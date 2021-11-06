package ru.geekbrains.spacepictures.view.test

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionManager
import ru.geekbrains.spacepictures.databinding.ActivityAnimationsBinding

private lateinit var binding: ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {
    private var textIsVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            button.setOnClickListener {
                TransitionManager.beginDelayedTransition(transitionsContainer)
//                TransitionManager.beginDelayedTransition(transitionsContainer, Slide(Gravity.END))
                textIsVisible = !textIsVisible
                text.visibility = if (textIsVisible) View.VISIBLE else View.GONE
            }
            button1.setOnClickListener {
                TransitionManager.beginDelayedTransition(transitionsContainer)
//                TransitionManager.beginDelayedTransition(transitionsContainer, Slide(Gravity.END))
                textIsVisible = !textIsVisible
                text.visibility = if (textIsVisible) View.VISIBLE else View.GONE
            }
        }
    }
}
