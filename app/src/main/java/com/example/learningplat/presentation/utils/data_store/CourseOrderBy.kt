package com.example.learningplat.presentation.utils.data_store

import kotlinx.serialization.Serializable


@Serializable
data class CourseOrderBy(
    val selectionPosition :Int = 0,
    val coursesOrderBySelection: CourseOrderBySelection
)

enum class CourseOrderBySelection (val orderByValue:String) {
    All("---"),
    MOST_REVIEWED("most-reviewed"),
    HIGHEST_RATED("highest-rated"),
    NEWEST("newest")
}

val courseOrderByIndexPositionSpinner = listOf(0,1,2,3,4,5)

enum class CourseOrderBySpinner (val orderByValue:String) {
    All("---"),
    MOST_REVIEWED("most reviewed"),
    HIGHEST_RATED("highest rated"),
    NEWEST("newest")
}