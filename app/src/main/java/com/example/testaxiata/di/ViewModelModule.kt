package com.example.testaxiata.di

import com.example.testaxiata.presentation.activity.detail_user.DetailUserViewModel
import com.example.testaxiata.presentation.activity.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailUserViewModel(get()) }

    viewModel { MainViewModel(get()) }
}