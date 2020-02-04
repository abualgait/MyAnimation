package com.abualgait.msayed.myanimation.extensions

import android.animation.*
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout


fun View.animateSuperMario() {
    val shoppingCartAnimatorX = ObjectAnimator.ofFloat(
        this,
        View.SCALE_X,
        1F,
        1.5F
    )
    shoppingCartAnimatorX.duration = 200L
    shoppingCartAnimatorX.repeatCount = 1
    shoppingCartAnimatorX.repeatMode = ValueAnimator.REVERSE
    val shoppingCartAnimatorY = ObjectAnimator.ofFloat(
        this,
        View.SCALE_Y,
        1F,
        1.5F
    )
    shoppingCartAnimatorY.duration = 200L
    shoppingCartAnimatorY.repeatCount = 1
    shoppingCartAnimatorY.repeatMode = ValueAnimator.REVERSE

    AnimatorSet().apply {
        play(shoppingCartAnimatorX).with(shoppingCartAnimatorY)
        start()
    }
}

fun View.objectAnimator(
    propertyName: String,
    durationToAnimate: Long,
    startValue: Float,
    endValue: Float
): ObjectAnimator {
    return ObjectAnimator.ofFloat(
        this,
        propertyName,
        startValue,
        endValue
    ).apply {
        interpolator = AccelerateDecelerateInterpolator()
        duration = durationToAnimate
    }
}


fun View.valueAnimator(
    image:ImageView,
    durationToAnimate: Long,
    startValue: Int,
    endValue: Int
): ValueAnimator {

    return ValueAnimator.ofInt(
        startValue,
        endValue
    ).apply {
            duration = durationToAnimate
            start()
            addUpdateListener { updatedAnimation ->
                val params =
                    image.layoutParams
                params.height = updatedAnimation.animatedValue as Int
                image.layoutParams = params
            }
        }
}


fun View.visible(animate: Boolean = true) {
    if (animate) {
        animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        super.onAnimationStart(animation)
                        visibility = View.VISIBLE
                    }
                }
            )
    } else {
        visibility = View.VISIBLE
    }
}

fun View.invisible(animate: Boolean = true) {
    if (animate) {
        animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        visibility = View.INVISIBLE
                    }
                }
            )
    } else {
        visibility = View.INVISIBLE
    }
}