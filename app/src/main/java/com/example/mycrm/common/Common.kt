package com.example.mycrm.common


import RetrofitServices
import com.example.mycrm.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "http://10.0.2.2:8080/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}