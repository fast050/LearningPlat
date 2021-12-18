package com.example.learningplat.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Courses(
    @SerializedName("_class")
    val classX: String?,
//        @SerializedName("curriculum_items")
//        val curriculumItems: List<Any?>?,
//        @SerializedName("curriculum_lectures")
//        val curriculumLectures: List<Any?>?,
    @SerializedName("headline")
    val headline: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_125_H")
    val image125H: String?,
    @SerializedName("image_240x135")
    val image240x135: String?,
    @SerializedName("image_480x270")
    val image480x270: String?,
//        @SerializedName("input_features")
//        val inputFeatures: Any?,
//        @SerializedName("instructor_name")
//        val instructorName: Any?,
    @SerializedName("is_paid")
    val isPaid: Boolean?,
    @SerializedName("is_practice_test_course")
    val isPracticeTestCourse: Boolean?,
//        @SerializedName("lecture_search_result")
//        val lectureSearchResult: Any?,
//        @SerializedName("order_in_results")
//        val orderInResults: Any?,
//        @SerializedName("predictive_score")
//        val predictiveScore: Any?,
    @SerializedName("price")
    val price: String?,
//        @SerializedName("price_detail")
//        val priceDetail: Any?,
    @SerializedName("price_serve_tracking_id")
    val priceServeTrackingId: String?,
    @SerializedName("published_title")
    val publishedTitle: String?,
//        @SerializedName("relevancy_score")
//        val relevancyScore: Any?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("tracking_id")
    val trackingId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("visible_instructors")
    val visibleInstructors: List<VisibleInstructor?>?
) : Parcelable {
    @Parcelize
    data class VisibleInstructor(
        @SerializedName("_class")
        val classX: String?,
        @SerializedName("display_name")
        val displayName: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image_100x100")
        val image100x100: String?,
        @SerializedName("image_50x50")
        val image50x50: String?,
        @SerializedName("initials")
        val initials: String?,
        @SerializedName("job_title")
        val jobTitle: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("url")
        val url: String?

    ) : Parcelable

}