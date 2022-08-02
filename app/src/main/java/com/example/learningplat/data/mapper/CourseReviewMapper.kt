package com.example.learningplat.data.mapper

import com.example.learningplat.data.network.dto.coursesreview.CourseReviewsDTO
import com.example.learningplat.domain.model.CourseReviews

fun CourseReviewsDTO.toCourseReviews(): CourseReviews {
    return CourseReviews(
        id = id,
        commentContent = content,
        courseRating = rating,
        commentCreatedAt = created,
        commentModifiedAt = modified,
        userTitle = user.title,
        userName = user.name,
        userDisplayName = user.display_name
    )
}