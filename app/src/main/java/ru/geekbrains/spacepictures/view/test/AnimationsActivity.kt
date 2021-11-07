package ru.geekbrains.spacepictures.view.test

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.ActivityAnimationsBinding

private lateinit var binding: ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {
    private var textIsVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = Adapter()

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
                text1.visibility = if (textIsVisible) View.VISIBLE else View.GONE
            }
        }
    }

    private fun explode(clickedView: View) {
        val viewRect = Rect()
        clickedView.getGlobalVisibleRect(viewRect)
        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.excludeTarget(clickedView, true)
        val set = TransitionSet()
            .addTransition(explode)
            .addTransition(Fade().addTarget(clickedView))
            .addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                    onBackPressed()
                }
            })
        TransitionManager.beginDelayedTransition(binding.recyclerView, set)
        binding.recyclerView.adapter = null
    }



            inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animations_explode_recycle_view_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                explode(it)
            }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
