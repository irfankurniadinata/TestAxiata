package com.example.testaxiata.presentation.activity.detail_user

import androidx.lifecycle.viewModelScope
import com.example.testaxiata.core.BaseViewModel
import com.example.testaxiata.data.model.User
import com.example.testaxiata.domain.usecase.GetDetailUserUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val getDetailUserUseCase: GetDetailUserUseCase
) : BaseViewModel(){

    private val _state = MutableStateFlow<DetailUserViewState>(DetailUserViewState.Init)
    val state: StateFlow<DetailUserViewState> get() = _state

    var idUser: Int = 0

    fun getDetailUser() {
        viewModelScope.launch {
            getDetailUserUseCase.execute(idUser)
                .onStart {  }
                .onEach { e ->

                }
                .collect { result ->
                    showDetailUser(result)
                }
        }
    }

    private fun showDetailUser(result: User) {
        _state.value = DetailUserViewState.ShowUser(result)
    }
}