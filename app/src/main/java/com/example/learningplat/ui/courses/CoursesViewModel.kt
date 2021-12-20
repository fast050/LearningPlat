package com.example.learningplat.ui.courses

import androidx.lifecycle.*
import com.example.learningplat.model.Courses
import com.example.learningplat.model.CoursesResponse
import com.example.learningplat.repository.CoursesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

enum class Price(val priceValue: String){
    FREE("price-free"),
    PAID("price-paid")
}

class CoursesViewModel(private val coursesRepository: CoursesRepository): ViewModel()
{

//    val courseItem = MutableLiveData<Courses>()
//    val

   private val _coursesList = MutableLiveData<List<Courses>>()
    val coursesList :LiveData<List<Courses>>
          get() = _coursesList

   private  val _errorMessage = MutableLiveData<String>()
    val errorMessage :LiveData<String>
    get() = _errorMessage


    fun getCourses(search:String?=null
                   ,page:String?=null,
                   category:String?=null,
                   priceType:Price?=null) {

        val response = coursesRepository.getCourses(search,page,category,priceType?.priceValue)

        response.enqueue(object : Callback<CoursesResponse>
        {
            override fun onResponse(
                call: Call<CoursesResponse>,
                response: Response<CoursesResponse>
            ) {
                if(response.code()==200)
                {
                    response.body()?.results?.let {

                        _coursesList.postValue(it)
                    }

                }

            }

            override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
               _errorMessage.postValue("Check Your Connection")
            }

        })
    }

    class CoursesViewModelFactory constructor(private val repository: CoursesRepository): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if(modelClass.isAssignableFrom(CoursesViewModel::class.java))
                CoursesViewModel(this.repository) as T
            else
                throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}