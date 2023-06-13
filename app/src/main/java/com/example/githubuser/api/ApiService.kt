package com.example.githubuser.api

import com.example.githubuser.response.DetailUserResponse
import com.example.githubuser.response.ItemsItem
import com.example.githubuser.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers (
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing (
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>
}