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

private const val PATH_TO_RESPONSE =
    "/Vaishnav-Kanhirathingal/salsa-you-ui-assignment/refs/heads/main/BackEnd/jsonResponse"

interface HomeApi {
    @GET("${PATH_TO_RESPONSE}/Home.json")
    suspend fun getPage(): List<HomeFeed>
}

interface SearchApi {
    @GET("${PATH_TO_RESPONSE}/search/Search.json")
    suspend fun mainPage(): List<SearchCategory>

    @GET("${PATH_TO_RESPONSE}/search/C{id}.json")
    suspend fun categoryPage(@Path("id") id: Int): List<SearchProfile>
}

interface UserProfileApi {
    @GET("${PATH_TO_RESPONSE}/profile.json")
    suspend fun getUserProfile(): UserProfile

    @GET("${PATH_TO_RESPONSE}/UserPosts.json")
    suspend fun getUserPosts(): List<String>
}