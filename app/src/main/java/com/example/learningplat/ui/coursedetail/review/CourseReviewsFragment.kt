package com.example.learningplat.ui.coursedetail.review

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.learningplat.databinding.CourseReviewsFragmentBinding
import com.example.learningplat.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseReviewsFragment : Fragment() {

    private val viewModel by viewModels<CourseReviewsViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private var _binding: CourseReviewsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CourseReviewsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.courseDetail.observe(viewLifecycleOwner) { course ->
            viewModel.setCourseReview(course.id)
            viewModel.getRate(course.id)
        }

        binding.apply {

            setRating()
            setRecyclerView()
        }
    }

    private fun CourseReviewsFragmentBinding.setRating() {
        viewModel.reviewRatingCount.observe(viewLifecycleOwner) {

            Log.d("TAG", "setRating: $it")

            excellentRatingBar.progress = it.rate_of_five_count
            goodRatingBar.progress = it.rate_of_four_count
            averageRatingBar.progress = it.rate_of_three_count
            belowAverageRatingBar.progress = it.rate_of_two_count
            poorRatingBar.progress = it.rate_of_one_count
        }

        viewModel.reviewsRatingOfAll.observe(viewLifecycleOwner) {
            courseRateNumber.text = it.toString()
        }
    }

    private fun CourseReviewsFragmentBinding.setRecyclerView() {

        val adapter = CourseReviewAdapter()
        reviewsRecyclerView.adapter = adapter

        viewModel.courseReviews.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}