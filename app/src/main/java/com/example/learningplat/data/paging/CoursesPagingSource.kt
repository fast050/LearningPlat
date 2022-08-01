package com.example.learningplat.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.learningplat.data.mapper.toCourses
import com.example.learningplat.presentation.utils.data_store.CoursesFilterSetting
import com.example.learningplat.data.network.api.CoursesApiService
import com.example.learningplat.domain.model.Courses
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoursesPagingSource @Inject constructor(
    private val coursesApiService: CoursesApiService,
    private val searchQuery: String?,
    private val filterSetting: CoursesFilterSetting?
) : PagingSource<Int, Courses>() {
    override fun getRefreshKey(state: PagingState<Int, Courses>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Courses> {
        val currentPageIndex = params.key ?: Courses_STARTING_PAGE_INDEX
        return try {

            val response = coursesApiService.getCourses(
                search = searchQuery,
                page = currentPageIndex.toString(),
                category = filterSetting?.coursesCategory?.coursesCategorySelection?.categoryValue,
                priceType = filterSetting?.coursePriceType?.coursePriceTypeSelection?.priceValue,
                courseLevel = filterSetting?.courseLevel?.courseLevelSelection?.levelValue,
                courseOrderBy = filterSetting?.courseOrderBy?.coursesOrderBySelection?.orderByValue,
                courseDurationType = filterSetting?.courseDurationType?.courseDurationTypeSelection?.durationValue
            )

            val data = response.body()?.results?.map { it.toCourses() } ?: emptyList()

            val prev = if (response.body()?.previous == null) null else currentPageIndex - 1
            val next = if (response.body()?.next == null) null else currentPageIndex + 1

            LoadResult.Page(data = data, prevKey = prev, nextKey = next)

        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }


    companion object {
        const val Courses_STARTING_PAGE_INDEX = 1
    }

}