package com.example.learningplat.repository

import com.example.learningplat.network.Service


class CoursesRepository(private val service: Service){


    fun getCourses(search:String?=null
                         ,page:String?=null,
                         category:String?=null,
                         priceType:String?=null) = service.getCourses(search,page,category,priceType)



}