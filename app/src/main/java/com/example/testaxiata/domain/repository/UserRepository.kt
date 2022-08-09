package com.example.testaxiata.domain.repository

import com.example.testaxiata.data.model.User
import com.example.testaxiata.network.BaseResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getListUser(page: Int, perPage: Int): Flow<BaseResponse<List<User>>>

    suspend fun getDetailUser(idUser: Int): Flow<User>
}