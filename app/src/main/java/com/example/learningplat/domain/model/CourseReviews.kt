package com.example.learningplat.domain.model

import com.example.learningplat.data.network.dto.coursesreview.User
import java.util.*

data class CourseReviews(
    val id: Int,
    val commentContent: String,
    val courseRating: Double,
    val commentCreatedAt: String,
    val commentModifiedAt: String,
    val userTitle: String,
    val userName: String,
    val userDisplayName: String
)


