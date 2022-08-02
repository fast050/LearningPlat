package com.example.learningplat.presentation.ui.courseslist

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.learningplat.presentation.utils.data_store.*
import com.example.learningplat.domain.repository.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class CoursesViewModel @Inject constructor(private val coursesRepository: CoursesRepository, filterSetting: FilterPreferences) : ViewModel() {

    val query = MutableLiveData<String?>(null)
    val priceType = MutableLiveData<CoursePriceType?>(null)
    val category = MutableLiveData<CourseCategory?>(null)
    val setting  =  filterSetting.filterSetting.asLiveData()

    val pagingCourses =
        (PairMediatorLiveData(query,setting))
            .switchMap { (query , setting)->
                coursesRepository.getPagedCourses(
                    search = query,
                    filterSetting = setting
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

class TripleMediatorLiveData<F, S, T>(
    firstLiveData: LiveData<F>,
    secondLiveData: LiveData<S>,
    thirdLiveData: LiveData<T>
) : MediatorLiveData<Triple<F?, S?, T?>>() {
    init {
        addSource(firstLiveData) { firstLiveDataValue: F -> value = Triple(firstLiveDataValue,
                                     secondLiveData.value, thirdLiveData.value) }

        addSource(secondLiveData) { secondLiveDataValue: S -> value = Triple(firstLiveData.value,
                                        secondLiveDataValue, thirdLiveData.value) }

        addSource(thirdLiveData) { thirdLiveDataValue: T -> value = Triple(firstLiveData.value,
                                      secondLiveData.value, thirdLiveDataValue) }
    }
}



