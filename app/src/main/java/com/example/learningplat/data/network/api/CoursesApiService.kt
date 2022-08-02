package com.example.learningplat.data.network.api

import com.example.learningplat.data.network.dto.courseslist.CoursesResponseEntity
import com.example.learningplat.data.network.dto.coursesreview.CourseReviewsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface CoursesApiService {

    @Headers(
        "Authorization: Basic ZVN5dUdFQnlqSkNSaXREVlN0VWVPbjZEdGZ1aTZSUk9Uc3p0ZEc2cjpsZmFqR3dTVnZB" +
                "Nk4wTXNvQlVaVGV5c25nZFZVSHlYZ09zYlB0dld3TTdvQzlJV1cyU1hYZlpCNFNrZHl3anUwUFJWblNEbz" +
                "J0MHFGV2JhSHNDSm4zUjZHQjBTNkVhT3k2YndXeTF0R0pZU3hRMk1JT2lNZGlBUlFQRjBDanhhRQ=="
    )


    @GET("courses/")
    suspend fun getCourses(
        @Query("search") search: String? = null,
        @Query("page") page: String? = null,
        @Query("category") category: String? = null,
        @Query("instructional_level") courseLevel:String?=null,
        @Query("price") priceType: String? = null,
        @Query("ordering") courseOrderBy :String?=null,
        @Query("duration") courseDurationType : String?=null

    ): Response<CoursesResponseEntity>

    @GET("courses/{course_id}/reviews")
    suspend fun getReviews(
        @Path("course_id") course_id:Int,
        @Query("page") page: String="1",
       @Query("page_size") pageSize:Int= 500
    ) :CourseReviewsResponseDTO



}

object CoursesApi {
    const val BaseURL = "https://www.udemy.com/api-2.0/"
}