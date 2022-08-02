package com.example.learningplat.commen

sealed class Resources<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T?=null) : Resources<T>(data = data)
    class Success<T>(data: T?=null) : Resources<T>(data = data)
    class Error<T>(message: String?=null) : Resources<T>(message = message)
}
