package com.example.learningplat.model
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CoursesResponse(
    @SerializedName("aggregations")
    val aggregations: List<Aggregation?>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Courses>?,
    @SerializedName("search_tracking_id")
    val searchTrackingId: String?
) : Parcelable {
    @Parcelize
    data class Aggregation(
        @SerializedName("id")
        val id: String?,
        @SerializedName("options")
        val options: List<Option?>?,
        @SerializedName("title")
        val title: String?
    ) : Parcelable {
        @Parcelize
        data class Option(
            @SerializedName("count")
            val count: Int?,
            @SerializedName("key")
            val key: String?,
            @SerializedName("title")
            val title: String?,
            @SerializedName("value")
            val value: String?
        ) : Parcelable
    }

}