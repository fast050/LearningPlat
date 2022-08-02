package com.example.learningplat.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Courses(
    val courseId:Int = 0,
    val courseTitle:String = "",
    val courseImage:String? = null,
    val coursePrice:String = "",
    val instructorName:String ="",
    val instructorTitle: String="",
    val instructorPicture:String="",
    val isPaidCourse:Boolean?=null,
    val courseUrl:String="",
  ):Parcelable
