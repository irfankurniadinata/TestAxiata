package com.example.testaxiata.di

import com.example.testaxiata.data.UserService
import com.example.testaxiata.domain.repository.UserRepository
import com.example.testaxiata.domain.repository.UserRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { provideUserService(get()) }
    single { provideUserRepository(get()) }
}

fun provideUserService(retrofit: Retrofit) : UserService{
    return retrofit.create(UserService::class.java)
}

fun provideUserRepository(service: UserService) : UserRepository{
    return UserRepositoryImpl(service)
}
