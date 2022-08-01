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

class GetReviewsListUseCase @Inject constructor(val repository: CoursesRepository) {

    suspend operator fun invoke(courseId: Int): Flow<Resources<List<CourseReviews>>> =
        repository.getReviewList(courseId)
}
