package com.example.learningplat.utils

import com.example.learningplat.data.model.Courses


private const val BaseURL = "https://www.udemy.com"

fun String.formatURL()= BaseURL+this

 const val Paid_Courses = "Paid"
 const val Free_Courses = "Free"

fun Courses.isPaid() = if (this.isPaid == true) Paid_Courses else Free_Courses