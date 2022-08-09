package com.example.testaxiata.presentation.activity.detail_user

import com.example.testaxiata.data.model.User

sealed class DetailUserViewState {
    object Init : DetailUserViewState()
    data class ShowUser(val user: User) : DetailUserViewState()
}
