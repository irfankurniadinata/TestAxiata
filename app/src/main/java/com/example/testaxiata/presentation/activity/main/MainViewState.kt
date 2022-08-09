package com.example.testaxiata.presentation.activity.main

import com.example.testaxiata.data.model.User

sealed class MainViewState {
    object Init : MainViewState()
    data class ShowError(val message: Throwable) : MainViewState()
    data class ShowUsers(val users: List<User>) : MainViewState()
}