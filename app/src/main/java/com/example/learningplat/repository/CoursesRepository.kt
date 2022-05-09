package com.example.learningplat.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.learningplat.data.CoursesPagingSource
import com.example.learningplat.data.model.Courses
import com.example.learningplat.data.network.CoursesApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface CoursesRepository {

    fun getPagedCourses(
        search: String? = null,
        category: String? = null,
        priceType: String? = null
    ): Flow<PagingData<Courses>>

}