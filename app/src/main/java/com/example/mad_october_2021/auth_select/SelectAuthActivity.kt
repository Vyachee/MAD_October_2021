package com.example.mad_october_2021.auth_select

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.mad_october_2021.R
import com.example.mad_october_2021.common.Bubble
import com.example.mad_october_2021.common.MoveDirection
import com.example.mad_october_2021.databinding.ActivitySelectAuthBinding
import com.example.mad_october_2021.sign_in.SignInActivity
import com.example.mad_october_2021.sign_up.SignUpActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SelectAuthActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectAuthBinding
    lateinit var bubbles: MutableList<Bubble>

    var bubbleWidth = 300
    var bubbleHeight = 300

    var containerLeftX = 0
    var containerTopY = 0
    var containerRightX = 0
    var containerBottomY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }


    private fun initBubbles() {

        val bubblesSources = arrayOf(
            R.drawable.e1,
            R.drawable.e2,
            R.drawable.e3,
            R.drawable.e4,
            R.drawable.e5,
            R.drawable.e6,
        )

        bubbles = mutableListOf()

        bubblesSources.forEach {
            bubbles.add(
                Bubble(
                    AppCompatResources.getDrawable(baseContext, it),
                    (Math.random() * 502 * Math.random()).toInt(),
                    (Math.random() * 521 * Math.random()).toInt(),
                    getRandomDirection()
                )
            )
        }

        drawBubbles()
        moveBubbles()
    }

    private fun initBorders() {

        binding.flBubbles.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.flBubbles.viewTreeObserver.removeOnGlobalLayoutListener(this)
                containerRightX = binding.flBubbles.width
                containerBottomY = binding.flBubbles.height
            }

        })
        initBubbles()
    }

    private fun getRandomDirection(): MoveDirection {
        val number = Math.random() * 100
        if (number < 25) return MoveDirection.LEFT_DOWN
        if (number in 25.0..50.0) return MoveDirection.LEFT_TOP
        if (number in 50.0..75.0) return MoveDirection.RIGHT_DOWN
        return MoveDirection.RIGHT_TOP
    }

    private fun getRandomDirection(d1: MoveDirection, d2: MoveDirection): MoveDirection {
        val number = Math.random() * 100
        return if (number <= 50) {
            d1
        } else {
            d2
        }
    }

    private fun moveBubbles() {
        bubbles.forEach {

            val rightBorder = it.x + 300
            val bottomBorder = it.y + 300
            if (it.x <= 0) it.x = 0
            if (it.y <= 0) it.y = 0

            if (it.x <= containerLeftX) {
                it.direction = getRandomDirection(MoveDirection.RIGHT_TOP, MoveDirection.RIGHT_DOWN)
            }

            if (rightBorder >= containerRightX) {
                it.direction = getRandomDirection(MoveDirection.LEFT_TOP, MoveDirection.LEFT_DOWN)
            }

            if (it.y <= containerTopY) {
                it.direction = getRandomDirection(MoveDirection.LEFT_DOWN, MoveDirection.RIGHT_DOWN)
            }

            if (bottomBorder >= containerBottomY) {
                it.direction = getRandomDirection(MoveDirection.RIGHT_TOP, MoveDirection.LEFT_TOP)
            }

            if (it.direction == MoveDirection.RIGHT_DOWN) {
                it.x = it.x + 3
                it.y = it.y + 3
            }

            if (it.direction == MoveDirection.RIGHT_TOP) {
                it.x = it.x + 3
                it.y = it.y - 3
            }

            if (it.direction == MoveDirection.LEFT_DOWN) {
                it.x = it.x - 3
                it.y = it.y + 3
            }

            if (it.direction == MoveDirection.LEFT_TOP) {
                it.x = it.x - 3
                it.y = it.y - 3
            }
        }

        drawBubbles()
        GlobalScope.launch {
            runOnUiThread {
                moveBubbles()
            }
        }
    }

    private fun drawBubbles() {
        binding.flBubbles.removeAllViews()

        bubbles.forEach {
            val bubbleIv = ImageView(baseContext)
            bubbleIv.setImageDrawable(it.drawable)

            val params = FrameLayout.LayoutParams(bubbleWidth, bubbleHeight)
            params.setMargins(it.x, it.y, 0, 0)
            bubbleIv.layoutParams = params
            binding.flBubbles.addView(bubbleIv)
        }
    }


    private fun setListeners() {

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(baseContext, SignUpActivity::class.java))
        }

        binding.tvSignIn.setOnClickListener {
            startActivity(Intent(baseContext, SignInActivity::class.java))
        }

        initBorders()
    }
}