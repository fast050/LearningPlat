package com.example.learningplat.data.model.coursesreview

data class CourseReviewResponse(
     val count : Int,
     val next:String,
     val previous:Int?,
     val results:List<CourseReviews>
)