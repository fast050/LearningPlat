package com.example.learningplat.ui.courseslist

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.learningplat.R
import com.example.learningplat.data.model.courseslist.Courses
import com.example.learningplat.launchFragmentInHiltContainer
import com.example.learningplat.ui.coursedetail.CourseDetailFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class CoursesListFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp()
    {
        hiltRule.inject()
    }

    @Test
    fun test_navigation_From_listCourses_to_DetailCourses() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<CoursesListFragment> {
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(this.requireView(), navController)
        }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.course_RecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5,click()))

        launchFragmentInHiltContainer<CourseDetailFragment> {

        }

        assertEquals(navController.currentDestination?.id, R.id.courseDetailFragment)
    }

    @Test
    fun test_ListFragment_Recycler_isDisplayed() {



      launchFragmentInHiltContainer<CoursesListFragment> {

      }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.course_RecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_progress_bar_isDisplayed(){

        launchFragmentInHiltContainer<CoursesListFragment> {}
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_menu_free_return_free_courses(){


        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<CoursesListFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withContentDescription("Sort")).perform(click())
        onView(withText("Free")).perform(click())

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.course_RecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))


        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val args = Bundle().apply {
            putParcelable("Course_Item", Courses(id = 1, price = "Free"))
        }

        launchFragmentInHiltContainer<CourseDetailFragment> {
            this.arguments = args
        }

        onView(withId(R.id.course_detail_text_price)).check(matches(withText(containsString("Free"))))
    }

    @Test
    fun test_menu_paid(){

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<CoursesListFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withContentDescription("Sort")).perform(click())
        onView(withText("Paid")).perform(click())

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.course_RecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))


        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val args = Bundle().apply {
            putParcelable("Course_Item", Courses(id = 1, price = "Paid"))
        }

        launchFragmentInHiltContainer<CourseDetailFragment> {
            this.arguments = args
        }

        onView(withId(R.id.course_detail_text_price)).check(matches(withText(containsString("$"))))

    }
}