package com.example.salsa.data

import com.example.salsa.models.home.HomeFeed
import com.example.salsa.models.profile.UserProfile
import com.example.salsa.models.search.SearchCategory
import com.example.salsa.models.search.SearchProfile
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object Apis {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val homeApi: HomeApi = retrofit.create(HomeApi::class.java)
    val searchApi: SearchApi = retrofit.create(SearchApi::class.java)
    val userProfileApi: UserProfileApi = retrofit.create(UserProfileApi::class.java)
}

interface HomeApi {
    @GET("/Vaishnav-Kanhirathingal/SalsaAssignment/refs/heads/main/BackEnd/jsonResponse/Home.json")
    suspend fun getPage(): List<HomeFeed>
}

interface SearchApi {
    @GET("/Vaishnav-Kanhirathingal/SalsaAssignment/refs/heads/main/BackEnd/jsonResponse/search/Search.json")
    suspend fun mainPage(): List<SearchCategory>

    @GET("/Vaishnav-Kanhirathingal/SalsaAssignment/refs/heads/main/BackEnd/jsonResponse/search/C{id}.json")
    suspend fun categoryPage(@Path("id") id: Int): List<SearchProfile>
}

interface UserProfileApi {
    @GET("/Vaishnav-Kanhirathingal/SalsaAssignment/refs/heads/main/BackEnd/jsonResponse/profile.json")
    suspend fun getUserProfile(): UserProfile

    @GET("/Vaishnav-Kanhirathingal/SalsaAssignment/refs/heads/main/BackEnd/jsonResponse/UserPosts.json")
    suspend fun getUserPosts(): List<String>
}