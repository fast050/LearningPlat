package com.example.learningplat.ui.courses

import androidx.lifecycle.*
import com.example.learningplat.model.Courses
import com.example.learningplat.model.CoursesResponse
import com.example.learningplat.repository.CoursesRepository
import kotlinx.coroutines.flow.combine
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

   private val _coursesList = MutableLiveData<List<Courses>>()


   private  val _errorMessage = MutableLiveData<String>()
    val errorMessage :LiveData<String>
    get() = _errorMessage

    val query = MutableLiveData<String?>(null)
    val priceType = MutableLiveData<Price?>(null)


    val getCourses = query.switchMap {
        _getCourses(search = it)
        _coursesList
    }

    val getCoursesByPrice = priceType.switchMap {
        _getCourses(search = query.value ,priceType = it)
        _coursesList
    }


    private fun _getCourses(search:String?=null
                            , page:String?=null,
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