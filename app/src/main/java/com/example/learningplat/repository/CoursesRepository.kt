package com.example.learningplat.repository

import androidx.paging.PagingData
import com.example.learningplat.data.model.courseslist.Courses
import com.example.learningplat.data.model.coursesreview.CourseReviewResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface CoursesRepository {

    fun getPagedCourses(
        search: String? = null,
        category: String? = null,
        priceType: String? = null
    ): Flow<PagingData<Courses>>

    suspend fun getReviewList(courseId:Int) : Response<CourseReviewResponse>



}