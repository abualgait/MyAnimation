package com.abualgait.msayed.myanimation

import ToolbarBehavior
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import kotlinx.android.synthetic.main.activity_complex_animation.*

class ComplexAnimation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex_animation)
        animateToolbar()

    }

    private fun animateToolbar() {
        (appbar.layoutParams as CoordinatorLayout.LayoutParams).behavior = ToolbarBehavior()
    }
}
