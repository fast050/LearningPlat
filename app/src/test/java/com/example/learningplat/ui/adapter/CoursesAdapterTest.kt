package com.example.learningplat.ui.adapter

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningplat.data.model.courseslist.Courses
import com.example.learningplat.data.network.CoursesApiService
import com.example.learningplat.repository.DefaultCoursesRepository
import com.example.learningplat.ui.courseslist.CoursesAdapter
import com.example.learningplat.ui.courseslist.CoursesViewModel
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
        viewmodel = CoursesViewModel(DefaultCoursesRepository(api))
    }

    @Test
    fun `test adapter size`() = runBlocking {




            val adapter = CoursesAdapter {}
            val dataList = listOf(
                Courses(id = 0, title = "moon"),
                Courses(id = 1, title = "sun")
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