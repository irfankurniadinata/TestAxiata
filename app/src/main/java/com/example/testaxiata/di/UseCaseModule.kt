package com.example.testaxiata.di

import com.example.testaxiata.domain.usecase.GetDetailUserUseCase
import com.example.testaxiata.domain.usecase.GetListUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetDetailUserUseCase(get()) }
    single { GetListUserUseCase(get()) }
}