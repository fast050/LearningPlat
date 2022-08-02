package com.example.learningplat.presentation.ui

import androidx.lifecycle.*
import com.example.learningplat.domain.model.Courses
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() :ViewModel(){

    val courseDetail = MutableLiveData<Courses>()

    fun setCourseDetail(courseClicked: Courses) {
       courseDetail.value=courseClicked
    }
}
