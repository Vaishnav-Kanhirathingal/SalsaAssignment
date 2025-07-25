package com.example.salsa.data

import com.example.salsa.models.home.HomeFeed
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object Apis {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val homeApi: HomeApi = retrofit.create(HomeApi::class.java)
}

interface HomeApi {
    @GET("/Vaishnav-Kanhirathingal/SalsaAssignment/refs/heads/main/BackEnd/jsonResponse/Home.json")
    suspend fun getPage(): List<HomeFeed>
}