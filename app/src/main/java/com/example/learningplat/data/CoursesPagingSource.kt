package com.example.learningplat.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.learningplat.data.model.courseslist.Courses
import com.example.learningplat.data.network.CoursesApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoursesPagingSource @Inject constructor(
    private val coursesApiService: CoursesApiService,
    private val searchQuery: String? = null,
    private val category: String? = null,
    private val priceType: String? = null
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
                category = category,
                priceType = priceType
            )

            val data = response.body()?.results ?: emptyList()

            val prev = if (response.body()?.previous == null) null else currentPageIndex - 1
            val next = if (response.body()?.next == null) null else currentPageIndex + 1

            LoadResult.Page(data = data, prevKey = prev, nextKey = next)

        } catch (e: HttpException) {
            LoadResult.Error(e)
        }catch (e:IOException) {
            LoadResult.Error(e)
        }
    }


    companion object {

        const val Courses_STARTING_PAGE_INDEX = 1

    }

}