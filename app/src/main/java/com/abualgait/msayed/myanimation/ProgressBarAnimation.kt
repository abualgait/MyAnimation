package com.abualgait.msayed.myanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

class ProgressBarAnimation(
    private val parent_view: LinearLayout,
    private val progressBar: ProgressBar,
    private val context: Context
) : Animation() {
    override fun applyTransformation(
        interpolatedTime: Float,
        t: Transformation
    ) {
        super.applyTransformation(interpolatedTime, t)
        val from = 0.0
        val to = 100.0
        val value = from + (to - from) * interpolatedTime
        progressBar.progress = value.toInt()
        val valueAdded = "${value.toInt()}%"


        // Create the new textview
        val newPercent = TextView(context)
        newPercent.text = valueAdded
        newPercent.textSize = 20f
        newPercent.maxLines = 1
        newPercent.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        val container = parent_view as ViewGroup
        container.addView(newPercent)

        val translator = ObjectAnimator.ofFloat(
            newPercent,
            View.TRANSLATION_X,
            100f,
            50f
        )
        translator.interpolator = AccelerateInterpolator(1f)


        val set = AnimatorSet()
        set.duration = 4000 / 50
        set.playTogether(translator)

        // When the animation is done, remove the created view from the container
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (value < 100)
                    container.removeView(newPercent)
            }
        })
        set.start()
    }


}