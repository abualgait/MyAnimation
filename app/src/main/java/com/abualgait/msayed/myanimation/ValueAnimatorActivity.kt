package com.abualgait.msayed.myanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abualgait.msayed.myanimation.extensions.getPositionOnScreen
import com.abualgait.msayed.myanimation.extensions.objectAnimator
import com.abualgait.msayed.myanimation.extensions.valueAnimator
import kotlinx.android.synthetic.main.activity_value_animator.*


class ValueAnimatorActivity : AppCompatActivity() {
    var played = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)
        activity_main_parent_view.viewTreeObserver.addOnGlobalLayoutListener {
            if (!played)
                animateTreeSize()
        }

    }

    private fun animateTreeSize() {

        played = true
        val pizzaViewPosition = mushroom.getPositionOnScreen()
        val shoppingCartPosition = mario.getPositionOnScreen()


        val yAnimator = mushroom.objectAnimator(
            propertyName = "translationY",
            durationToAnimate = ANIMATION_DURATION,
            startValue = 0f,
            endValue = shoppingCartPosition[1].toFloat()
        )
        val alphaAnimator = mushroom.objectAnimator(
            propertyName = "alpha",
            durationToAnimate = 500L,
            startValue = 1f,
            endValue = 0f
        )

        AnimatorSet().apply {
            play(yAnimator).before(alphaAnimator)
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                 //   activity_main_parent_view.removeView(mushroom)
                    mario.valueAnimator(
                        mario,
                        ANIMATION_DURATION,
                        mario.measuredHeight,
                        mario.measuredHeight * 4
                    )
                }
            })
            start()
        }


    }

    companion object {
        const val ANIMATION_DURATION = 2000L
    }
}
