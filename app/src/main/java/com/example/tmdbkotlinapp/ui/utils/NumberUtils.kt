package com.example.tmdbkotlinapp.ui.utils

fun formatFloat(number: Float, numberOfDigits: Int = 1): Float {
    return String.format("%.${numberOfDigits}f", number).replace(",", ".").toFloat()
}