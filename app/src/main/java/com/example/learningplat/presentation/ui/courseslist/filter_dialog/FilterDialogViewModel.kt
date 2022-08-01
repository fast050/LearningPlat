package com.example.learningplat.presentation.ui.courseslist.filter_dialog

import androidx.lifecycle.*
import com.example.learningplat.presentation.utils.data_store.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterDialogViewModel @Inject constructor(private val filterSetting: FilterPreferences) :
    ViewModel() {

    private var _coursesFilterSetting: CoursesFilterSetting = CoursesFilterSetting()
    val coursesFilterSetting get() = _coursesFilterSetting

    init {
        viewModelScope.launch {
            filterSetting.filterSetting.collectLatest {
                _coursesFilterSetting = it
            }
        }
    }

    fun setSetting(
        category: CourseCategory? = _coursesFilterSetting.coursesCategory,
        priceType: CoursePriceType? = _coursesFilterSetting.coursePriceType,
        orderBy: CourseOrderBy? = _coursesFilterSetting.courseOrderBy,
        level: CourseLevel? = _coursesFilterSetting.courseLevel,
        durationType: CourseDurationType? = _coursesFilterSetting.courseDurationType
    ) = viewModelScope.launch {

        filterSetting.setFilterSetting(category, priceType, orderBy, level, durationType)
    }

    fun setOrderByPick(orderBy: String?, selectionPosition: Int) {

        if (selectionPosition > courseOrderByIndexPositionSpinner.size)
            return

        when (orderBy) {
            CourseOrderBySpinner.All.orderByValue -> {
                setSetting(
                    orderBy = CourseOrderBy(
                        selectionPosition = selectionPosition,
                        coursesOrderBySelection = CourseOrderBySelection.All
                    )
                )
            }
            CourseOrderBySpinner.MOST_REVIEWED.orderByValue -> {
                setSetting(
                    orderBy = CourseOrderBy(
                        selectionPosition = selectionPosition,
                        coursesOrderBySelection = CourseOrderBySelection.MOST_REVIEWED
                    )
                )

            }
            CourseOrderBySpinner.HIGHEST_RATED.orderByValue -> {
                setSetting(
                    orderBy = CourseOrderBy(
                        selectionPosition = selectionPosition,
                        coursesOrderBySelection = CourseOrderBySelection.HIGHEST_RATED
                    )
                )

            }
            CourseOrderBySpinner.NEWEST.orderByValue -> {
                setSetting(
                    orderBy = CourseOrderBy(
                        selectionPosition = selectionPosition,
                        coursesOrderBySelection = CourseOrderBySelection.NEWEST
                    )
                )
            }
            else -> {

            }
        }
    }

    fun setCategory(chipText: CharSequence, chipId: Int) {
        when (chipText) {
            CoursesCategorySelection.BUSINESS.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.BUSINESS
                    )
                )
            }
            CoursesCategorySelection.LIFESTYLE.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.LIFESTYLE
                    )
                )
            }
            CoursesCategorySelection.IT_SOFTWARE.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.IT_SOFTWARE
                    )
                )
            }
            CoursesCategorySelection.DESIGN.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.DESIGN
                    )
                )
            }
            CoursesCategorySelection.TEACHING_ACADEMICS.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.TEACHING_ACADEMICS
                    )
                )
            }
            CoursesCategorySelection.MUSIC.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.MUSIC
                    )
                )
            }
            CoursesCategorySelection.DEVELOPMENT.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.DEVELOPMENT
                    )
                )
            }
            CoursesCategorySelection.OFFICE_PRODUCTIVITY.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.OFFICE_PRODUCTIVITY
                    )
                )
            }
            CoursesCategorySelection.FINANCE_ACCOUNTING.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.FINANCE_ACCOUNTING
                    )
                )
            }
            CoursesCategorySelection.HEALTH_FITNESS.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.HEALTH_FITNESS
                    )
                )
            }
            CoursesCategorySelection.PERSONAL_DEVELOPMENT.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.PERSONAL_DEVELOPMENT
                    )
                )
            }
            CoursesCategorySelection.PHOTOGRAPHY_VIDEO.categoryValue -> {
                setSetting(
                    category = CourseCategory(
                        buttonSelectedId = chipId,
                        coursesCategorySelection = CoursesCategorySelection.PHOTOGRAPHY_VIDEO
                    )
                )
            }
            else -> {

            }
        }
    }


}