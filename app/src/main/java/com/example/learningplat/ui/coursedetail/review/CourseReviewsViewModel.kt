package com.example.learningplat.ui.coursedetail.review

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningplat.data.model.coursesreview.CourseReviews
import com.example.learningplat.data.model.coursesreview.ReviewsRating
import com.example.learningplat.repository.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseReviewsViewModel @Inject constructor
    (private val coursesRepository: CoursesRepository) : ViewModel() {

    val courseReviews: LiveData<List<CourseReviews>>
        get() = _courseReviewlist

    private val _courseReviewlist = MutableLiveData<List<CourseReviews>>()

    fun setCourseReview(courseId: Int) = viewModelScope.launch {
        val response = coursesRepository.getReviewList(courseId)

        if (response.isSuccessful)
            _courseReviewlist.value =
                response.body()?.results?.sortedBy { it.content.isEmpty() } ?: emptyList()
    }

    private val _reviewRatingCount = MutableLiveData<ReviewsRating>()
    val reviewRatingCount: LiveData<ReviewsRating> = _reviewRatingCount

    private val _reviewRatingOfAll = MutableLiveData<Double>()
    val reviewsRatingOfAll: LiveData<Double> = _reviewRatingOfAll

    fun getRate(courseId: Int) = viewModelScope.launch {

        val reviewList = coursesRepository.getReviewList(courseId).body()?.results ?: return@launch

        val rateOfFive = reviewList.filter { it.rating > 4.0 && it.rating <= 5.0 }.size.toDouble()
            .div(reviewList.size).times(100).toInt()

        val rateOfFour = reviewList.filter { it.rating > 3.0 && it.rating <= 4.0 }.size.toDouble()
            .div(reviewList.size).times(100).toInt()

        val rateOfThree = reviewList.filter { it.rating > 2.0 && it.rating <= 3.0 }.size.toDouble()
            .div(reviewList.size).times(100).toInt()

        val rateOfTwo = reviewList.filter { it.rating > 1.0 && it.rating <= 2.0 }.size.toDouble()
            .div(reviewList.size).times(100).toInt()

        val rateOfOne =
            reviewList.filter { it.rating in 0.0..1.0 }.size.toDouble().div(reviewList.size)
                .times(100).toInt()

        _reviewRatingCount.value =
            ReviewsRating(rateOfFive, rateOfFour, rateOfThree, rateOfTwo, rateOfOne)

        Log.d("TAG", "getRate 5: ${reviewList.filter{ it.rating > 4.0 && it.rating <= 5.0 }.size}")
        Log.d("TAG", "getRate 4: ${reviewList.filter{ it.rating > 3.0 && it.rating <= 4.0 }.size}")
        Log.d("TAG", "getRate 3: ${reviewList.filter{ it.rating > 2.0 && it.rating <= 3.0 }.size}")
        Log.d("TAG", "getRate 2: ${reviewList.filter{ it.rating > 1.0 && it.rating <= 2.0 }.size}")
        Log.d("TAG", "getRate 1: ${reviewList.filter{ it.rating  in 0.0..1.0}.size}")


        val averageReviewRating =reviewList.sumOf { it.rating }.div(reviewList.size)
        _reviewRatingOfAll.value = String.format("%.1f", averageReviewRating).toDouble()


    }


}