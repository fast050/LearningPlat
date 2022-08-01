package com.example.learningplat.presentation.ui.coursedetail


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.example.learningplat.databinding.FragmentCourseDetailBinding
import com.example.learningplat.presentation.ui.coursedetail.info.CourseInfoFragment
import com.example.learningplat.presentation.ui.coursedetail.review.CourseReviewsFragment

import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!
    val viewModel by viewModels<CourseDetailViewModel>()

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

            Log.d("TAG", "onViewCreated: ${viewModel.courseDetailItem?.courseTitle}")

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
            childFragmentManager ,
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