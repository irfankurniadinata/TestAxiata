package com.example.testaxiata.presentation.activity.main

import com.example.testaxiata.core.BaseViewModel
import com.example.testaxiata.domain.usecase.GetListUserUseCase
import androidx.lifecycle.viewModelScope
import com.example.testaxiata.data.local.UsersRepository
import com.example.testaxiata.data.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getListUserUseCase: GetListUserUseCase
): BaseViewModel() {
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Init)
    val state: StateFlow<MainViewState> get() = _state

    var page : Int = 1
    var totalPages: Int = 0

    fun getListUser() {
        viewModelScope.launch {
            getListUserUseCase.execute(page = page, perPage = 8)
                .onStart {  }
                .catch { e ->
                    showError(e)
                }
                .collect { result ->
                    totalPages = result.totalPages ?: 0
                    result.data?.let { showListUser(it) }
                    if (page == 1) {
                        UsersRepository.setUser(result.data)
                    }
                }
        }
    }

    private fun showError(e: Throwable) {
        _state.value = MainViewState.ShowError(e)
    }

    private fun showListUser(result: List<User>) {
        _state.value = MainViewState.ShowUsers(result)
    }
}