package com.example.testaxiata.domain.usecase

import com.example.testaxiata.domain.repository.UserRepository
import com.example.testaxiata.data.model.User
import kotlinx.coroutines.flow.Flow

class GetDetailUserUseCase (private val userRepository: UserRepository) {
    suspend fun execute(idUser: Int): Flow<User> {
        return userRepository.getDetailUser(idUser)
    }
}