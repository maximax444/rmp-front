package com.example.mycrm

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
    @GET("projects/my")
    suspend fun getAllProducts(): List<Project>
    @POST("projects/tasks/get")
    suspend fun getTasks(@Body projectIdDTO: ProjectIdDTO): List<Project>
    @POST("user/login")
    suspend fun login(@Body user: User): String
}