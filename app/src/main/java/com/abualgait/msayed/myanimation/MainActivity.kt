package com.abualgait.msayed.myanimation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this, GmailProfile::class.java))

        }

        button2.setOnClickListener {
            startActivity(Intent(this, DayNight::class.java))

        }

        button3.setOnClickListener {
            startActivity(Intent(this, ProductiveFamilies::class.java))

        }

        button4.setOnClickListener {
            startActivity(Intent(this, ValueAnimatorActivity::class.java))

        }

        complex.setOnClickListener {
            startActivity(Intent(this, ComplexAnimation::class.java))

        }
    }
}
