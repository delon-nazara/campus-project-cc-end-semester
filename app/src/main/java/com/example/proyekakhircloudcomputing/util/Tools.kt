package com.example.proyekakhircloudcomputing.util

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun formatName(name: String): String {
    return name
        .lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { firstLetter ->
                firstLetter.uppercase()
            }
        }
}

fun getFirstWord(name: String): String {
    return name.split(" ").first()
}

fun getFirstChar(name: String): Char {
    return name[0].lowercaseChar()
}