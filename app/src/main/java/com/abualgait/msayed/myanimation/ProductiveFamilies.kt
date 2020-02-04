package com.abualgait.msayed.myanimation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.abualgait.msayed.myanimation.extensions.getPositionOnScreen
import com.abualgait.msayed.myanimation.extensions.objectAnimator
import kotlinx.android.synthetic.main.activity_productive_families.*


class ProductiveFamilies : AppCompatActivity() {

    val vegetables = arrayOf(
        R.drawable.picture1,
        R.drawable.picture2,
        R.drawable.picture3,
        R.drawable.picture4,
        R.drawable.picture5,
        R.drawable.picture6,
        R.drawable.picture7,
        R.drawable.picture8
    )

    val stophunger = arrayOf(
        "S",
        "t",
        "o",
        "p",
        "H",
        "u",
        "n",
        "g",
        "e",
        "r"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productive_families)

        playProgressAnimation()

        //add listener to view layout to launch animation after view positioning
        dishView.viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() { // Layout has happened here.

                    fillVegteablesDish()
                    dishView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
    }


    private fun fillVegteablesDish() {

        val dishPosition = dishView.getPositionOnScreen()


        // Local variables we'll need in the code below
        val container = dishView.parent as ViewGroup
        val containerW = container.width


        // Create the new star (an ImageView holding our drawable) and add it to the container
        for (vegetable in vegetables) {

            val xAnimator: ObjectAnimator
            val yAnimator: ObjectAnimator
            val scaleDownX: ObjectAnimator
            val scaleDownY: ObjectAnimator

            val newVegetable = AppCompatImageView(this)
            newVegetable.setImageResource(vegetable)
            newVegetable.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            container.addView(newVegetable)

            // Position the view at a random place between the left and right edges of the container
            newVegetable.translationX = Math.random().toFloat() * containerW
            val vegPostition = newVegetable.getPositionOnScreen()


            xAnimator = newVegetable.objectAnimator(
                propertyName = "translationX",
                durationToAnimate = ANIMATION_DURATION,
                startValue = vegPostition[0].toFloat(),
                endValue = dishPosition[0].toFloat() + Math.random().toFloat() * 1.5f + .1f
            )
            yAnimator = newVegetable.objectAnimator(
                propertyName = "translationY",
                durationToAnimate = ANIMATION_DURATION,
                startValue = vegPostition[1].toFloat() - dishView.measuredHeight,
                endValue = dishPosition[1].toFloat() - dishView.measuredHeight
            )
            scaleDownX = ObjectAnimator.ofFloat(
                newVegetable,
                "scaleX",
                0.5F
            )

            scaleDownY = ObjectAnimator.ofFloat(
                newVegetable,
                "scaleY",
                0.5F
            )

            AnimatorSet().apply {
                play(xAnimator).with(yAnimator).with(scaleDownX).with(scaleDownY)
                start()
            }

        }
        //val letterContainerW = lettersContainer.width
        val stopHungerPostion = lettersContainer.getPositionOnScreen()
        stophunger.forEachIndexed { index, element ->


            val newLetter = TextView(this)
            newLetter.text = element
            newLetter.textSize = 25f
            newLetter.typeface = Typeface.DEFAULT_BOLD
            val color =
                if ("stop".contains(
                        element,
                        ignoreCase = true
                    )
                ) resources.getColor(R.color.colorAccent) else resources.getColor(
                    R.color.colorPrimary
                )
            newLetter.setTextColor(color)
            newLetter.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            lettersContainer.addView(newLetter)

            val yAnimator: ObjectAnimator


            yAnimator = newLetter.objectAnimator(
                propertyName = "translationY",
                durationToAnimate = ANIMATION_DURATION,
                startValue = if (index % 2 == 0) 50f else -50f,
                endValue = 0f
            )
            AnimatorSet().apply {
                play(yAnimator)
                start()
            }

        }
    }

    private fun playProgressAnimation() {

        val anim = ProgressBarAnimation(
            linearLayout,
            progressBar, this
        )
        anim.duration = ANIMATION_DURATION
        progressBar!!.startAnimation(anim)
    }

    companion object {
        const val ANIMATION_DURATION = 2000L
    }
}
