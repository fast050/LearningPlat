package com.example.learningplat.domain.use_cases

import com.example.learningplat.domain.model.CourseReviews
import com.example.learningplat.domain.repository.CoursesRepository
import retrofit2.HttpException
import javax.inject.Inject

class GetReviewsRatingAverageUseCase @Inject constructor(val repository: CoursesRepository) {

    operator fun invoke(courseReviews: List<CourseReviews>): Double {
        val averageReviewRating = courseReviews.sumOf { it.courseRating }.div(courseReviews.size)
        return formatDoubleToOneDecimalPlace(averageReviewRating)
    }

    fun formatDoubleToOneDecimalPlace(averageReviewRating: Double): Double {
        return String.format("%.1f", averageReviewRating).toDouble()
    }
}