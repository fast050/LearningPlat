package com.example.learningplat.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.learningplat.data.CoursesPagingSource
import com.example.learningplat.data.model.courseslist.Courses
import com.example.learningplat.data.network.CoursesApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultCoursesRepository @Inject constructor(private val coursesApi:CoursesApiService) :CoursesRepository {

    override fun getPagedCourses(
        search: String?,
        category: String?,
        priceType: String?
    ): Flow<PagingData<Courses>> {
        return  Pager(PagingConfig(10,2,false)
            , pagingSourceFactory = { CoursesPagingSource(coursesApi,search,category,priceType) }).flow
    }

   override suspend fun getReviewList(courseId:Int) = coursesApi.getReviews(course_id = courseId)
}