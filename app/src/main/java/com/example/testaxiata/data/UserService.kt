package com.example.testaxiata.data

import com.example.testaxiata.data.model.User
import com.example.testaxiata.network.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UserService {
    @GET("api/users")
    suspend fun getListUser(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<BaseResponse<List<User>>>

    @GET("api/users/{idUser}")
    suspend fun getDetailUser(
        @Path("idUser") idUser: Int
    ): Response<BaseResponse<User>>
}