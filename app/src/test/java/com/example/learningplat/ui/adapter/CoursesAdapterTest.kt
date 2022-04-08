package com.example.learningplat.ui.adapter

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningplat.data.model.Courses
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CoursesAdapterTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val context = Mockito.mock(Context::class.java)


    @Test
    fun `test adapter size`(){

     val adapter = CoursesAdapter{context}
        val dataList = listOf(
            Courses(id = 0, title = "moon"),
            Courses(id = 1, title = "sun")
        )
        adapter.submitList(dataList)
        val size=adapter.itemCount

        assertEquals("the Courses adapter dont have the same item size as the list pass",size,dataList.size)
    }

}