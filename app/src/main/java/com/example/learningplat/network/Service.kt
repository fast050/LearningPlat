package com.example.learningplat.network

import androidx.lifecycle.LiveData
import com.example.learningplat.model.Courses
import com.example.learningplat.model.CoursesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {

    @Headers(
        "Authorization: Basic ZVN5dUdFQnlqSkNSaXREVlN0VWVPbjZEdGZ1aTZSUk9Uc3p0ZEc2cjpsZmFqR3dTVnZB" +
                "Nk4wTXNvQlVaVGV5c25nZFZVSHlYZ09zYlB0dld3TTdvQzlJV1cyU1hYZlpCNFNrZHl3anUwUFJWblNEbz" +
                "J0MHFGV2JhSHNDSm4zUjZHQjBTNkVhT3k2YndXeTF0R0pZU3hRMk1JT2lNZGlBUlFQRjBDanhhRQ=="
    )
    @GET("courses/")
     fun getCourses(
        @Query("search") search: String?=null,
        @Query("page") page: String?=null,
        @Query("category") category: String?=null,
        @Query("price") priceType: String? =null

    ): Call<CoursesResponse>


    companion object {
        private const val BaseURL = "https://www.udemy.com/api-2.0/"
        fun getInstance(): Service = Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(Service::class.java)

    }



}