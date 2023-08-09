package com.example.tmdbkotlinapp.ui.utils

class NumberUtils {
    companion object {
        fun formatFloat(number: Float, numberOfDigits: Int? = null): Float {
            return if (numberOfDigits == null) {
                String.format("%.1f", number).replace(",", ".").toFloat()
            } else
                String.format("%.$numberOfDigits", number).replace(",", ".").toFloat()
        }
    }
}