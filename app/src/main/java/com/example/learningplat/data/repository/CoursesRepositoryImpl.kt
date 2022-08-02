package com.example.learningplat.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.learningplat.commen.Resources
import com.example.learningplat.data.mapper.toCourseReviews
import com.example.learningplat.presentation.utils.data_store.CoursesFilterSetting
import com.example.learningplat.data.paging.CoursesPagingSource
import com.example.learningplat.data.network.api.CoursesApiService
import com.example.learningplat.domain.model.CourseReviews
import com.example.learningplat.domain.model.Courses
import com.example.learningplat.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(private val coursesApi: CoursesApiService) :
    CoursesRepository {

    override fun getPagedCourses(
        search: String?,
        filterSetting: CoursesFilterSetting?
    ): Flow<PagingData<Courses>> {
        return Pager(PagingConfig(10, 2, false),
            pagingSourceFactory = { CoursesPagingSource(coursesApi, search, filterSetting) }).flow
    }

    override suspend fun getReviewList(courseId: Int): Flow<Resources<List<CourseReviews>>> = flow {

        emit(Resources.Loading())
        try {
            val response = coursesApi.getReviews(course_id = courseId).results
            emit(Resources.Success(data = response.map { it.toCourseReviews()}))

        }catch (e:HttpException){
            emit(Resources.Error(message = e.message ?: "Opps! something went wrong"))
        }
        catch (e:IOException){
            emit(Resources.Error(message = e.message ?: "Check your internet connection"))
        }
    }

}