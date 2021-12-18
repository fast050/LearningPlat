package com.example.learningplat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.learningplat.network.Service
import com.example.learningplat.utils.formatURL
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       GlobalScope.launch {

           val service= Service.getInstance()


           try{
               val courseResponse = service.getCourses().results
               if (courseResponse != null) {
                   Log.d(TAG, "onCreate: size of :  ${courseResponse.size}")
                   for(course in courseResponse){
                       course?.let {

                           Log.d(TAG, "onCreate: ${course.title}  /- ${course.id}  ")
                           Log.d(TAG, "onCreate: ${course.url?.formatURL()}  /- ${course.price}  ")

                       }
                   }
               }
           }catch (e: HttpException)
           {
               Log.d(TAG, "onCreate: ${e.response()}")
           }

       }



    }
}