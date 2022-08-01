package com.example.learningplat.presentation.ui.coursedetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CourseDetailPagerAdapter(
    fragmentList: List<Fragment>,
    fragmentName: List<String>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: List<Fragment> = fragmentList

    private val _fragmentsName : List<String> = fragmentName

    val fragmentName get() = _fragmentsName


    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]


}