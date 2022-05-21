package com.example.learningplat.data.model.courseslist
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity

@Parcelize
@Entity
data class CoursesResponse(
    @SerializedName("aggregations")
    val aggregations: List<Aggregation?>?=null,
    @SerializedName("count")
    val count: Int?=null,
    @SerializedName("next")
    val next: String?=null,
    @SerializedName("previous")
    val previous: String?=null,
    @SerializedName("results")
    val results: List<Courses>?=null,
    @SerializedName("search_tracking_id")
    val searchTrackingId: String?=null
) : Parcelable {
    @Parcelize
    data class Aggregation(
        @SerializedName("id")
        val id: String?=null,
        @SerializedName("options")
        val options: List<Option?>?=null,
        @SerializedName("title")
        val title: String?=null
    ) : Parcelable {
        @Parcelize
        data class Option(
            @SerializedName("count")
            val count: Int?=null,
            @SerializedName("key")
            val key: String?=null,
            @SerializedName("title")
            val title: String?=null,
            @SerializedName("value")
            val value: String?=null
        ) : Parcelable
    }

}