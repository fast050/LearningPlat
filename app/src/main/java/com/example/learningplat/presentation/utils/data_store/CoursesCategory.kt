package com.example.learningplat.presentation.utils.data_store

import kotlinx.serialization.Serializable

@Serializable
data class CourseCategory(
    val buttonSelectedId:Int=-1,
    val coursesCategorySelection: CoursesCategorySelection?,
)

enum class CoursesCategorySelection(val categoryValue: String) {
    BUSINESS("Business"),
    DEVELOPMENT("Development"),
    DESIGN("Design"),
    FINANCE_ACCOUNTING("Finance & Accounting"),
    HEALTH_FITNESS("Health & Fitness"),
    IT_SOFTWARE("IT & Software"),
    LIFESTYLE("Lifestyle"),
    MUSIC("Music"),
    PERSONAL_DEVELOPMENT("Personal Development"),
    OFFICE_PRODUCTIVITY("Office Productivity"),
    PHOTOGRAPHY_VIDEO("Photography & Video"),
    TEACHING_ACADEMICS("Teaching & Academics")
}