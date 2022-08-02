package com.example.learningplat.presentation.ui.coursedetail.review

import android.util.Log
import androidx.lifecycle.*
import com.example.learningplat.commen.Resources
import com.example.learningplat.data.network.dto.coursesreview.ReviewsRating
import com.example.learningplat.domain.model.CourseReviews
import com.example.learningplat.domain.use_cases.GetReviewsListUseCase
import com.example.learningplat.domain.use_cases.GetReviewsRatingAverageUseCase
import com.example.learningplat.domain.use_cases.GetReviewsRatingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseReviewsViewModel @Inject constructor
    (
   private val getReviewsRatingUseCase: GetReviewsRatingUseCase,
   private val getReviewsRatingAverageUseCase: GetReviewsRatingAverageUseCase,
   private val getReviewsListUseCase: GetReviewsListUseCase
) : ViewModel() {


    val courseReviews: LiveData<Resources<List<CourseReviews>>>
        get() = _courseReviewList
    private val _courseReviewList = MutableLiveData<Resources<List<CourseReviews>>>()
    fun getReviewsList(courseId: Int) = viewModelScope.launch {
        getReviewsListUseCase(courseId).collectLatest {
            _courseReviewList.value = it
        }
    }

    private val _reviewRating = MutableLiveData<Resources<ReviewsRating>>()
    val reviewRating: LiveData<Resources<ReviewsRating>> = _reviewRating
    fun getReviewRating(courseReviews: List<CourseReviews>) = viewModelScope.launch {

        getReviewsRatingUseCase(courseReviews).collectLatest {
            _reviewRating.value = it
        }
    }

    private val _reviewRatingAverageNumber = MutableLiveData<Double>()
    val reviewsRatingAverageNumber: LiveData<Double> = _reviewRatingAverageNumber

    fun getReviewRatingAverageNumber(courseReviews: List<CourseReviews>) {
        val averageReviewRating = getReviewsRatingAverageUseCase(courseReviews)
        _reviewRatingAverageNumber.value = averageReviewRating
    }

    private val _reviewRatingAverageStar = MutableLiveData<Double>()
    val reviewsRatingAverageStar: LiveData<Double> = _reviewRatingAverageStar

    fun getReviewRatingAverageStar(courseReviews: List<CourseReviews>) {
        _reviewRatingAverageNumber.value = getReviewsRatingAverageUseCase(courseReviews)
    }



}