package com.example.learningplat.data.network.dto.coursesreview

import com.example.learningplat.domain.model.CourseReviews
import java.util.*

data class CourseReviewsDTO(
    val id: Int,
    val content: String,
    val rating: Double,
    val created: String,
    val modified: String,
    val user: User,
)

data class User(
    val title: String,
    val name: String,
    val display_name: String
)


