package com.example.learningplat.ui.courses

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.learningplat.data.model.Courses
import com.example.learningplat.repository.CoursesRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

enum class Price(val priceValue: String) {
    FREE("price-free"),
    PAID("price-paid")
}

class CoursesViewModel(private val coursesRepository: CoursesRepository) : ViewModel() {

    val query = MutableLiveData<String?>(null)
    val priceType = MutableLiveData<Price?>(null)

    val pagingCourses = (PairMediatorLiveData(query, priceType)).switchMap {

        coursesRepository.getPagedCourses(
            priceType = it.second?.priceValue,
            search = it.first
            ).cachedIn(viewModelScope).asLiveData()


    }

    class CoursesViewModelFactory constructor(private val repository: CoursesRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CoursesViewModel::class.java))
                CoursesViewModel(this.repository) as T
            else
                throw IllegalArgumentException("ViewModel Not Found")
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
}