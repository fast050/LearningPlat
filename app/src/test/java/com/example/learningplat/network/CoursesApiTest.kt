package com.example.learningplat.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.learningplat.data.network.CoursesApi
import com.example.learningplat.data.network.CoursesApiService
import com.example.learningplat.repository.DefaultCoursesRepository
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class CoursesApiTest {

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000

    lateinit var coursesApiCall: CoursesApiService

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
    fun `APIs parse correctly`() = runBlocking {
        server.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("courses_response.json").content))
        }
        val data = coursesApiCall.getCourses().body()
        assertThat(data).isNotNull()
    }

    @Test
    fun `API response have the right size`() = runBlocking {
        server.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("courses_response.json").content))
        }
        val dataSize = coursesApiCall.getCourses().body()?.results?.size
        assertThat(dataSize).isEqualTo(10)
    }

    @Test
    fun `API response failed`() = runBlocking {
        server.apply {
            dispatcher = object :Dispatcher(){
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return MockResponse().setResponseCode(404).setBody(MockResponseFileReader("courses_response.json").content)
                }

            }
        }
        val data = coursesApiCall.getCourses()
        assertThat(data.body()).isNull()
        assertThat(data.code()).isEqualTo(404)
    }

}
