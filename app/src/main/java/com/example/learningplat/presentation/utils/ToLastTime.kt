package com.example.learningplat.presentation.utils

import java.util.*

/**will return the time of the comment in this form " 1 day ago" or " 1 year ago "*/
fun String.toLastTime(): String {
    val lastCharacter = this.indexOf("T")
    val s = this.substring(0, lastCharacter)

    val year = s.substring(0, 4).toInt()
    val month = s.substring(5, 7).toInt()
    val day = s.substring(8, 10).toInt()

    // Using Calendar - calculating number of months between two dates
    val birthDay = GregorianCalendar(year, month - 1, day)
    val today = GregorianCalendar()
    today.time = Date()
    val yearsInBetween = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR)
    val monthsDiff = today.get(Calendar.MONTH) - birthDay.get(Calendar.MONTH)
    val daysDiff = today.get(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH)

    return when {
        yearsInBetween > 0 -> "$yearsInBetween year ago"
        monthsDiff > 0 -> "$monthsDiff month ago"
        else -> "$daysDiff day ago"
    }
}
