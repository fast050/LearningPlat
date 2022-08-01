package com.example.learningplat.domain.repository

import androidx.paging.PagingData
import com.example.learningplat.commen.Resources
import com.example.learningplat.presentation.utils.data_store.CoursesFilterSetting
import com.example.learningplat.domain.model.CourseReviews
import com.example.learningplat.domain.model.Courses
import kotlinx.coroutines.flow.Flow


interface CoursesRepository {

    fun getPagedCourses(
        search: String? = null,
        filterSetting: CoursesFilterSetting?
    ): Flow<PagingData<Courses>>

    suspend fun getReviewList(courseId:Int) : Flow<Resources<List<CourseReviews>>>



}