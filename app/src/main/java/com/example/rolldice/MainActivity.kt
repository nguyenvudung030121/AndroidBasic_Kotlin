package com.example.rolldice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var ivDice: ImageView
    private lateinit var rollAnimation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivDice = findViewById(R.id.ivDice)
        rollAnimation = AnimationUtils.loadAnimation(this, R.anim.roll_animation)

        val btnRoll: Button = findViewById(R.id.btnRoll)
        btnRoll.setOnClickListener {
            rollDice()
        }
    }
    private fun rollDice() {

        ivDice.startAnimation(rollAnimation)

        // Delay the result display to match the animation duration
        Handler().postDelayed({
            val drawableId = when (Random.nextInt(1, 7)) {
                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                else -> R.drawable.dice6
            }
            ivDice.setImageResource(drawableId)
        }, rollAnimation.duration)
    }
}