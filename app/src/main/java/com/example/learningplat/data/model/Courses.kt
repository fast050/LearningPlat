package com.example.learningplat.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Courses(
    @SerializedName("_class")
    val classX: String?=null,
//        @SerializedName("curriculum_items")
//        val curriculumItems: List<Any?>?,
//        @SerializedName("curriculum_lectures")
//        val curriculumLectures: List<Any?>?,
    @SerializedName("headline")
    val headline: String?=null,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("image_125_H")
    val image125H: String?=null,
    @SerializedName("image_240x135")
    val image240x135: String?=null,
    @SerializedName("image_480x270")
    val image480x270: String?=null,
//        @SerializedName("input_features")
//        val inputFeatures: Any?,
//        @SerializedName("instructor_name")
//        val instructorName: Any?,
    @SerializedName("is_paid")
    val isPaid: Boolean?=null,
    @SerializedName("is_practice_test_course")
    val isPracticeTestCourse: Boolean?=null,
//        @SerializedName("lecture_search_result")
//        val lectureSearchResult: Any?,
//        @SerializedName("order_in_results")
//        val orderInResults: Any?,
//        @SerializedName("predictive_score")
//        val predictiveScore: Any?,
    @SerializedName("price")
    val price: String?=null,
//        @SerializedName("price_detail")
//        val priceDetail: Any?,
    @SerializedName("price_serve_tracking_id")
    val priceServeTrackingId: String?=null,
    @SerializedName("published_title")
    val publishedTitle: String?=null,
//        @SerializedName("relevancy_score")
//        val relevancyScore: Any?,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("tracking_id")
    val trackingId: String?=null,
    @SerializedName("url")
    val url: String?=null,
    @SerializedName("visible_instructors")
    val visibleInstructors: List<VisibleInstructor?>?=null
) : Parcelable


@Parcelize
data class VisibleInstructor(
    @SerializedName("_class")
    val classX: String?=null,
    @SerializedName("display_name")
    val displayName: String?=null,
    @SerializedName("id")
    val id: Int?=null,
    @SerializedName("image_100x100")
    val image100x100: String?=null,
    @SerializedName("image_50x50")
    val image50x50: String?=null,
    @SerializedName("initials")
    val initials: String?=null,
    @SerializedName("job_title")
    val jobTitle: String?=null,
    @SerializedName("name")
    val name: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("url")
    val url: String?=null

) : Parcelable