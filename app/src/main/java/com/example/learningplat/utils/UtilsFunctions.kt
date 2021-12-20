package com.example.learningplat.utils

import com.example.learningplat.model.Courses


private const val BaseURL = "https://www.udemy.com"

fun String.formatURL()= BaseURL+this

fun Courses.isPaid() = if (this.isPaid == true) "Paid" else "Free"