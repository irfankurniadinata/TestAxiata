package com.example.testaxiata.presentation.adapter

import android.view.View

interface AdapterClickListener<T> {
    fun onItemClick(data : T)
    fun onViewClick(view : View, data : T)
}