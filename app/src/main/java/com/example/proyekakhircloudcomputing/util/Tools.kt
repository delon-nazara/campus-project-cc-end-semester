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

fun getFirstLetter(name: String): Char {
    return name[0].lowercaseChar()
}

fun convertMillisecondsToLargestUnit(milliseconds: Long): String {
    val msInSecond: Long = 1000
    val msInMinute: Long = msInSecond * 60
    val msInHour: Long = msInMinute * 60
    val msInDay: Long = msInHour * 24
    val msInMonth: Long = msInDay * 30
    val msInYear: Long = msInDay * 365

    return when {
        milliseconds >= msInYear -> "${milliseconds / msInYear} tahun"
        milliseconds >= msInMonth -> "${milliseconds / msInMonth} bulan"
        milliseconds >= msInDay -> "${milliseconds / msInDay} hari"
        milliseconds >= msInHour -> "${milliseconds / msInHour} jam"
        milliseconds >= msInMinute -> "${milliseconds / msInMinute} menit"
        else -> "${milliseconds / msInSecond} detik"
    }
}