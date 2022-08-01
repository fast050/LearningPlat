package com.example.learningplat.data.network.dto.courseslist

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learningplat.domain.model.Courses
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class CoursesEntity(
    @SerializedName("_class")
    val classX: String? = null,
    @SerializedName("headline")
    val headline: String? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_125_H")
    val image125H: String? = null,
    @SerializedName("image_240x135")
    val image240x135: String? = null,
    @SerializedName("image_480x270")
    val image480x270: String? = null,
    @SerializedName("is_paid")
    val isPaid: Boolean? = null,
    @SerializedName("is_practice_test_course")
    val isPracticeTestCourse: Boolean? = null,

    @SerializedName("order_in_results")
    val orderInResults: Int?,

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("price_serve_tracking_id")
    val priceServeTrackingId: String? = null,
    @SerializedName("published_title")
    val publishedTitle: String? = null,

    @SerializedName("title")
    val title: String? = null,
    @SerializedName("tracking_id")
    val trackingId: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("visible_instructors")
    val visibleInstructors: List<VisibleInstructor?>? = null
)

data class VisibleInstructor(
    @SerializedName("_class")
    val classX: String? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image_100x100")
    val image100x100: String? = null,
    @SerializedName("image_50x50")
    val image50x50: String? = null,
    @SerializedName("initials")
    val initials: String? = null,
    @SerializedName("job_title")
    val jobTitle: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null
)