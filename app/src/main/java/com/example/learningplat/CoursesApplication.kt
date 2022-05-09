package com.example.learningplat

import android.app.Application
import com.example.learningplat.data.network.CoursesApi
import com.example.learningplat.repository.CoursesRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoursesApplication : Application()