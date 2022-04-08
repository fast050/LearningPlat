package com.example.learningplat.ui.courses

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learningplat.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoursesListFragmentTest{


    lateinit var fragmentSenario : FragmentScenario<CoursesListFragment>

    @Before
    fun setUp()
    {
        fragmentSenario= launchFragmentInContainer(themeResId = R.style.Theme_LearningPlat)
    }


    @Test
    fun test_ListFragment_Recycler_isDisplayed() {

        fragmentSenario.moveToState(Lifecycle.State.CREATED)

        // onView(withId(R.id.course_detail_image_course_picture)).check(matches(isDisplayed()))
        onView(withId(R.id.course_RecyclerView)).check(matches(isDisplayed()))


    }
}