package com.example.learningplat.presentation.ui.coursedetail.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learningplat.domain.model.Courses
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CourseInfoViewModel @Inject constructor() :
    ViewModel() {

    val courseItem :LiveData<Courses>
          get() = _courseItem

    private val _courseItem = MutableLiveData<Courses>()

    fun setCourseItem(courseItem: Courses) {
        _courseItem.value = courseItem
    }
}