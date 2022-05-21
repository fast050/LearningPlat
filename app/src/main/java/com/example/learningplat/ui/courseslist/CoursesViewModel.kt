package com.example.learningplat.ui.courseslist

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.learningplat.data.model.coursesreview.CourseReviews
import com.example.learningplat.repository.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class Price(val priceValue: String) {
    FREE("price-free"),
    PAID("price-paid")
}

@HiltViewModel
class CoursesViewModel @Inject constructor(private val coursesRepository: CoursesRepository) : ViewModel() {

    val query = MutableLiveData<String?>(null)
    val priceType = MutableLiveData<Price?>(null)

    val pagingCourses = (PairMediatorLiveData(query, priceType)).switchMap {

        coursesRepository.getPagedCourses(
            priceType = it.second?.priceValue,
            search = it.first
            ).cachedIn(viewModelScope).asLiveData()
    }

}

class PairMediatorLiveData<F, S>(firstLiveData: LiveData<F>, secondLiveData: LiveData<S>) :
    MediatorLiveData<Pair<F?, S?>>() {
    init {
        addSource(firstLiveData) { firstLiveDataValue: F ->
            value = firstLiveDataValue to secondLiveData.value
        }
        addSource(secondLiveData) { secondLiveDataValue: S ->
            value = firstLiveData.value to secondLiveDataValue
        }
    }
}