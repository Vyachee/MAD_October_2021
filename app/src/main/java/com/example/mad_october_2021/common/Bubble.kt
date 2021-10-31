package com.example.mad_october_2021.common

import android.graphics.drawable.Drawable

class Bubble(
    val drawable: Drawable?,
    var x: Int,
    var y: Int,
    var direction: MoveDirection
)

enum class MoveDirection {
    LEFT_TOP,
    LEFT_DOWN,
    RIGHT_TOP,
    RIGHT_DOWN
}