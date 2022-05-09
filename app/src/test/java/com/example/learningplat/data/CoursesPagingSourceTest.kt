package com.example.learningplat.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.learningplat.data.model.Courses
import com.example.learningplat.data.network.CoursesApiService
import com.example.learningplat.network.MockResponseFileReader
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// how to test paging 3 !!

//@OptIn(ExperimentalCoroutinesApi::class)
class CoursesPagingSourceTest {

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000

    lateinit var coursesApiCall: CoursesApiService

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @get:Rule
    val instance = InstantTaskExecutorRule()

    @Before
    fun init() {
        server.start(MOCK_WEBSERVER_PORT)

        coursesApiCall = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(CoursesApiService::class.java)
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    @Test
    fun `get courses test page source`()= runBlockingTest{

        server.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("courses_response.json").content))
        }

            val result = CoursesPagingSource(coursesApiCall).load(
                PagingSource.LoadParams.Refresh(key = null, loadSize = 2, placeholdersEnabled = false)
            )

            val data =  coursesApiCall.getCourses().body()?.results ?: emptyList()

            assertThat(result).isEqualTo(
                PagingSource.LoadResult.Page(data=data, prevKey = null, nextKey = 2)
            )

    }
}