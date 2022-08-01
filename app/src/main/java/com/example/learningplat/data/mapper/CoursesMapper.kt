package com.example.learningplat.data.mapper

import com.example.learningplat.data.network.dto.courseslist.CoursesEntity
import com.example.learningplat.domain.model.Courses

fun CoursesEntity.toCourses(): Courses {
    return Courses(
        courseId = id,
        courseTitle = title ?: "",
        courseImage = image480x270,
        coursePrice = price?:"",
        instructorName = visibleInstructors?.first()?.name?:"",
        instructorTitle = visibleInstructors?.first()?.title?:"",
        isPaidCourse = isPaid,
        instructorPicture = visibleInstructors?.first()?.image100x100 ?:"",
        courseUrl = url?:""
    )
}

