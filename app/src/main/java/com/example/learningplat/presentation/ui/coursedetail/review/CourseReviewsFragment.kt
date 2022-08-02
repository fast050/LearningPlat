package com.example.learningplat.presentation.ui.coursedetail.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.learningplat.commen.Resources
import com.example.learningplat.data.network.dto.coursesreview.ReviewsRating
import com.example.learningplat.databinding.CourseReviewsFragmentBinding
import com.example.learningplat.presentation.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseReviewsFragment : Fragment() {

    private val viewModel by viewModels<CourseReviewsViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private var _binding: CourseReviewsFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = CourseReviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CourseReviewsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.reviewsNestedScrollView.isVisible = false

        sharedViewModel.courseDetail.observe(viewLifecycleOwner) { course ->
            viewModel.getReviewsList(course.courseId)
        }

        viewModel.courseReviews.observe(viewLifecycleOwner) {

            binding.reviewsRecyclerView.isVisible = it is Resources.Success
            it.data?.let { reviewList ->
                adapter.submitList(reviewList)
                viewModel.getReviewRating(reviewList)
                viewModel.getReviewRatingAverageNumber(reviewList)
                viewModel.getReviewRatingAverageStar(reviewList)
            }
        }

        binding.apply {
            setReviewRatingScreen()
            setReviewsRecyclerView()
        }
    }

    private fun CourseReviewsFragmentBinding.setReviewRatingScreen() {
        setReviewsViews()
        setReviewsRatingText()
        setReviewRatingStars()
    }

    private fun CourseReviewsFragmentBinding.setReviewRatingStars() {
        viewModel.reviewsRatingAverageStar.observe(viewLifecycleOwner) {
            courseRateStars.rating = it.toFloat()
        }
    }

    private fun CourseReviewsFragmentBinding.setReviewsRatingText() {
        viewModel.reviewsRatingAverageNumber.observe(viewLifecycleOwner) {
            courseRateNumber.text = it.toString()
        }
    }

    private fun CourseReviewsFragmentBinding.setReviewsViews() {
        viewModel.reviewRating.observe(viewLifecycleOwner) {


            setMainProgressBarVisibility(it)
            setReviewsScrollViewVisibility(it)

            it.data?.let {
                    data ->setReviewRatingBarData(data)
            }
        }
    }

    private fun CourseReviewsFragmentBinding.setReviewsScrollViewVisibility(it: Resources<ReviewsRating>?) {
        reviewsNestedScrollView.isVisible = it is Resources.Success
    }


    private fun CourseReviewsFragmentBinding.setReviewRatingBarData(data: ReviewsRating) {
        excellentRatingBar.progress = data.rate_of_five_count
        goodRatingBar.progress = data.rate_of_four_count
        averageRatingBar.progress = data.rate_of_three_count
        belowAverageRatingBar.progress = data.rate_of_two_count
        poorRatingBar.progress = data.rate_of_one_count

    }

    private fun CourseReviewsFragmentBinding.setMainProgressBarVisibility(it: Resources<ReviewsRating>?) {
        mainReviewsProgressBar.isVisible = it is Resources.Loading
    }


    private fun CourseReviewsFragmentBinding.setReviewsRecyclerView() {
        reviewsRecyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


