package com.example.learningplat.presentation.ui.adapter

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningplat.data.network.api.CoursesApiService
import com.example.learningplat.data.repository.CoursesRepositoryImpl
import com.example.learningplat.domain.model.Courses
import com.example.learningplat.presentation.ui.courseslist.CoursesAdapter
import com.example.learningplat.presentation.ui.courseslist.CoursesViewModel
import com.example.learningplat.presentation.utils.data_store.FilterPreferences
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CoursesAdapterTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var api: CoursesApiService

    private val context = Mockito.mock(Context::class.java)

    lateinit var viewmodel: CoursesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewmodel = CoursesViewModel(CoursesRepositoryImpl(api), filterSetting = FilterPreferences(context))
    }

    @Test
    fun `test adapter size`() = runBlocking {




            val adapter = CoursesAdapter {}
            val dataList = listOf(
                Courses(courseId = 0, courseTitle = "moon"),
                Courses(courseId = 1, courseTitle = "sun")
            )
            // can be either Dispatchers.Default or Dispatchers.IO but not Dispatchers.Main
            // enter code here
            viewmodel.pagingCourses.observeForever { }
           // adapter.submitData()

            val size = adapter.itemCount

            assertEquals(
                "the Courses adapter dont have the same item size as the list pass",
                size,
                dataList.size
            )

    }

}