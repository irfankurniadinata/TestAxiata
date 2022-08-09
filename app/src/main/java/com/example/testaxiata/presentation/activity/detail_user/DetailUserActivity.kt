package com.example.testaxiata.presentation.activity.detail_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.testaxiata.R
import com.example.testaxiata.data.model.User
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetailUserViewModel>()

    lateinit var ivUser: AppCompatImageView
    lateinit var tvName: AppCompatTextView
    lateinit var tvEmail: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        initView()
        subscribeState()

    }

    private fun initView() {
        viewModel.idUser = intent.getIntExtra("id_user", 0)
        viewModel.getDetailUser()

        ivUser = findViewById(R.id.ivUser)
        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
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

    private fun handleFlowState(state: DetailUserViewState) {
        when(state) {
            is DetailUserViewState.Init -> onInitState()
            is DetailUserViewState.ShowUser -> onShowUser(state.user)
        }
    }

    private fun onShowUser(user: User) {
        Glide.with(this)
            .load(user.avatar)
            .into(ivUser)
        tvName.text = user.first_name + " " + user.last_name
        tvEmail.text = user.email
    }

    private fun onInitState() {

    }
}