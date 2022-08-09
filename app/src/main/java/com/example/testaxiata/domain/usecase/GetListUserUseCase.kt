package com.example.testaxiata.domain.usecase

import com.example.testaxiata.domain.repository.UserRepository
import com.example.testaxiata.data.model.User
import com.example.testaxiata.network.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetListUserUseCase (private val userRepository: UserRepository){
    suspend fun execute(page: Int, perPage: Int): Flow<BaseResponse<List<User>>> {
        return userRepository.getListUser(page, perPage)
    }
}