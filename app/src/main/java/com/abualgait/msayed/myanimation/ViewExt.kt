package com.abualgait.msayed.myanimation

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Created at Tito on 2019-07-21
 */

fun View.getPositionOnScreen(): IntArray {
    val position = intArrayOf(0, 0)
    getLocationOnScreen(position)
    return position
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