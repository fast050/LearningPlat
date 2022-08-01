package com.example.learningplat.presentation.utils.data_store

import kotlinx.serialization.Serializable

@Serializable
data class CourseLevel(
    val buttonSelectedId:Int=-1,
    val courseLevelSelection: CourseLevelSelection
)

enum class CourseLevelSelection(val levelValue:String) {
    ALL("all"),
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    EXPERT("expert")
}