package com.example.testaxiata.presentation.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testaxiata.R
import com.example.testaxiata.data.model.User
import com.example.testaxiata.presentation.activity.detail_user.DetailUserActivity
import com.example.testaxiata.presentation.adapter.AdapterClickListener
import com.example.testaxiata.presentation.adapter.UserAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapterUser = UserAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<User> {
            override fun onItemClick(data: User) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra("id_user", data.id)
                startActivity(intent)
            }

            override fun onViewClick(view: View, data: User) {

            }

        }
    }

    private val viewModel by viewModel<MainViewModel>()

    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getListUser()
        setupAdapter()
        subscribeState()
    }

    private fun setupAdapter() {
        val rvUser: RecyclerView = findViewById(R.id.rvUser)
        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
        rvUser.apply {
            layoutManager = linearLayoutManager
            adapter = adapterUser
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisiblePosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    val itemCount = (rvUser.adapter?.itemCount ?: 0) - 1
                    if (!isLoading && lastVisiblePosition == itemCount) {
                        if (viewModel.page < viewModel.totalPages) {
                            viewModel.page = viewModel.page + 1
                            isLoading = true
                            viewModel.getListUser()
                        }
                    }
                }
            })
        }
    }

    private fun subscribeState() {
        lifecycleScope.launch {
            viewModel.state
                .onEach { state ->
                    handleFlowState(state)
                }
                .launchIn(this)

        }
    }

    private fun handleFlowState(state: MainViewState) {
        when (state) {
            is MainViewState.Init -> onInitState()
            is MainViewState.ShowError -> onShowError(state.message)
            is MainViewState.ShowUsers -> onShowUsers(state.users)
        }
    }

    private fun onShowError(message: Throwable) {
        isLoading = false
    }

    private fun onShowUsers(users: List<User>) {
        isLoading = false
        adapterUser.addData(users)
    }

    private fun onInitState() {

    }
}