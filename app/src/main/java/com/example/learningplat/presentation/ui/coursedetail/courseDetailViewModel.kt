package com.example.learningplat.presentation.ui.coursedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.learningplat.domain.model.Courses
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class CourseDetailViewModel @AssistedInject constructor(@Assisted state: SavedStateHandle) :
    ViewModel() {

    val courseDetailItem = state.get<Courses>("courseDetailItem")
}