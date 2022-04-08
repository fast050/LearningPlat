package com.example.learningplat.utils

import com.example.learningplat.data.model.Courses
import junit.framework.Assert.*
import org.junit.Test


class UtilsFunctionsKtTest
{

    @Test
    fun `test isPaid() courseIsPaid true return Paid`() {

        val course = Courses(isPaid = true)

        val actual = course.isPaid()

        assertEquals(actual, Paid_Courses)

    }

    @Test
    fun `test isPaid() courseIsPaid false return Free`() {

        val course = Courses(isPaid = false)

        val actual = course.isPaid()

        assertEquals(actual, Free_Courses)

    }
}