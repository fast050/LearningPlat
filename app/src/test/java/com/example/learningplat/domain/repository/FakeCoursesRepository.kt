package com.example.learningplat.domain.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.paging.PagingData
import com.example.learningplat.commen.Resources
import com.example.learningplat.domain.model.CourseReviews
import com.example.learningplat.domain.model.Courses
import com.example.learningplat.presentation.utils.data_store.CoursesFilterSetting
import kotlinx.coroutines.flow.Flow

 class FakeCoursesRepository :CoursesRepository{

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value :Boolean){
        shouldReturnNetworkError = value
    }

    private val pagingList = MutableLiveData<PagingData<Courses>>()


     override fun getPagedCourses(
         search: String?,
         filterSetting: CoursesFilterSetting?
     ): Flow<PagingData<Courses>> {
         return pagingList.asFlow()
     }

     override suspend fun getReviewList(courseId: Int): Flow<Resources<List<CourseReviews>>> {
         TODO("Not yet implemented")
     }


 }