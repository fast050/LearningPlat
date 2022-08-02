package com.example.learningplat.data.network.dto.coursesreview

data class CourseReviewsResponseDTO(
     val count : Int,
     val next:String,
     val previous:Int?,
     val results:List<CourseReviewsDTO>
)