package com.example.learningplat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningplat.databinding.ActivityMainBinding
import com.example.learningplat.network.Service
import com.example.learningplat.ui.adapter.CoursesAdapter
import com.example.learningplat.utils.formatURL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

       // setContentView(R.layout.activity_main)
        val adapter = CoursesAdapter()

       GlobalScope.launch {

           val service= Service.getInstance()




           try{
               val courseList = service.getCourses().results
               if (courseList != null) {
                   withContext(Dispatchers.Main){
                       binding.courseRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                       binding.courseRecyclerView.adapter=adapter
                       adapter.submitList(courseList)
                   }

               }
           }catch (e: HttpException)
           {
               Log.d(TAG, "onCreate: ${e.response()}")
           }

       }



    }
}