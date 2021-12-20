package com.example.learningplat

import android.app.Application
import com.example.learningplat.network.Service
import com.example.learningplat.repository.CoursesRepository

class CoursesApplication:Application()
{
    //val database by lazy { WordRoomDatabase.getDatabase(this) }

    val repository by lazy { CoursesRepository(Service.getInstance())}
}