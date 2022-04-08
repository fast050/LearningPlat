package com.example.learningplat.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.learningplat.data.CoursesPagingSource
import com.example.learningplat.data.model.Courses
import com.example.learningplat.data.network.CoursesApiService
import kotlinx.coroutines.flow.Flow


class CoursesRepository(private val coursesApi: CoursesApiService){


//   suspend fun getCourses(search:String?=null
//                         ,page:String?=null,
//                         category:String?=null,
//                         priceType:String?=null) = coursesApi.getCourses(search,page,category,priceType)
//
    fun getPagedCourses(search:String?=null,
                                category:String?=null,
                                priceType:String?=null): Flow<PagingData<Courses>> {
      return  Pager(PagingConfig(10,2,false)
        , pagingSourceFactory = {CoursesPagingSource(coursesApi,search,category,priceType)}).flow
    }



}