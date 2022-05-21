package com.example.learningplat.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learningplat.data.model.courseslist.Courses
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() :ViewModel(){

    private val _courseDetail = MutableLiveData<Courses>()
    val courseDetail :LiveData<Courses>
        get() = _courseDetail

    fun setCourseDetail(courseDetail:Courses){
        _courseDetail.value = courseDetail
    }

}