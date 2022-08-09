package com.example.testaxiata.domain.repository

import com.example.testaxiata.data.UserService
import com.example.testaxiata.data.local.UsersRepository
import com.example.testaxiata.data.model.User
import com.example.testaxiata.network.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UserRepositoryImpl(private val userService: UserService) : UserRepository{
    override suspend fun getListUser(page: Int, perPage: Int): Flow<BaseResponse<List<User>>> {
        return flow {
            try {
                val response = userService.getListUser(page, perPage)
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let { emit(it) }
                } else {
                    UsersRepository.getUser()?.let {
                        emit(BaseResponse(it))
                    }
                }
            } catch (e: Exception) {
                UsersRepository.getUser()?.let {
                    emit(BaseResponse(it))
                }
            }
        }
    }

    override suspend fun getDetailUser(idUser: Int): Flow<User> {
        return flow {
            val response = userService.getDetailUser(idUser)
            if (response.isSuccessful) {
                val body = response.body()
                body?.data?.let { emit(it) }
            } else {

            }
        }
    }

}