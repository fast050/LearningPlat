package com.example.learningplat.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.paging.PagingData
import com.example.learningplat.data.model.Courses
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.*

class FakeCoursesRepository :CoursesRepository{

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value :Boolean){
        shouldReturnNetworkError = value
    }

    private val pagingList = MutableLiveData<PagingData<Courses>>()

    override fun getPagedCourses(
        search: String?,
        category: String?,
        priceType: String?
    ): Flow<PagingData<Courses>> {
        return pagingList.asFlow()
    }


}