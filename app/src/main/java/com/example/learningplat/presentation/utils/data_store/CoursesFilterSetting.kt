package com.example.learningplat.presentation.utils.data_store

import kotlinx.serialization.Serializable

@Serializable
data class CoursesFilterSetting(
    val coursesCategory: CourseCategory? = null,
    val coursePriceType: CoursePriceType? = null,
    val courseLevel: CourseLevel?=null,
    val courseOrderBy: CourseOrderBy?=null,
    val courseDurationType: CourseDurationType?=null,
)
