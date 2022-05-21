package com.example.learningplat.ui.coursedetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.learningplat.databinding.FragmentCourseDetailBinding
import com.example.learningplat.ui.coursedetail.info.CourseInfoFragment
import com.example.learningplat.ui.coursedetail.review.CourseReviewsFragment

import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            setupPagerView()

        }
    }

    private fun FragmentCourseDetailBinding.setupPagerView() {
        val fragmentList = listOf(
            CourseInfoFragment(), CourseReviewsFragment()
        )
        val fragmentName = listOf(
            "Details", "Reviews"
        )

        val pagerAdapter = CourseDetailPagerAdapter(
            fragmentList,
            fragmentName,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        detailPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, detailPager) { tab, position ->
            tab.text = pagerAdapter.fragmentName[position]
        }.attach()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}