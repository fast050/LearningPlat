package com.example.learningplat.domain.use_cases

import com.example.learningplat.commen.Resources
import com.example.learningplat.data.network.dto.coursesreview.ReviewsRating
import com.example.learningplat.domain.model.CourseReviews
import com.example.learningplat.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetReviewsRatingUseCase @Inject constructor(val repository: CoursesRepository) {
    suspend operator fun invoke(reviewsList: List<CourseReviews>): Flow<Resources<ReviewsRating>> = flow {


      emit(Resources.Loading())

            val rateOfFive = getRateOfFive(reviewsList)
            val rateOfFour = getRateOfFour(reviewsList)
            val rateOfThree = getRateOfThree(reviewsList)
            val rateOfTwo = getRateOfTwo(reviewsList)
            val rateOfOne = getRateOfOne(reviewsList)

            emit(
                Resources.Success(
                    data = ReviewsRating(
                        rateOfFive,
                        rateOfFour,
                        rateOfThree,
                        rateOfTwo,
                        rateOfOne
                    )
                )
            )


    }

    private fun getRateOfOne(reviewsList: List<CourseReviews>) =
        reviewsList.filter { it.courseRating in 0.0..1.0 }.size.toDouble().div(reviewsList.size)
            .times(100).toInt()

    private fun getRateOfTwo(reviewsList: List<CourseReviews>) =
        reviewsList.filter { it.courseRating > 1.0 && it.courseRating <= 2.0 }.size.toDouble()
            .div(reviewsList.size).times(100).toInt()

    private fun getRateOfThree(reviewsList: List<CourseReviews>) =
        reviewsList.filter { it.courseRating > 2.0 && it.courseRating <= 3.0 }.size.toDouble()
            .div(reviewsList.size).times(100).toInt()

    private fun getRateOfFour(reviewsList: List<CourseReviews>) =
        reviewsList.filter { it.courseRating > 3.0 && it.courseRating <= 4.0 }.size.toDouble()
            .div(reviewsList.size).times(100).toInt()

    private fun getRateOfFive(reviewsList: List<CourseReviews>) =
        reviewsList.filter { it.courseRating > 4.0 && it.courseRating <= 5.0 }.size.toDouble()
            .div(reviewsList.size).times(100).toInt()


}