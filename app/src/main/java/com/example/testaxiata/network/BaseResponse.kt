package com.example.testaxiata.network

import com.google.gson.annotations.SerializedName

/**
 * This class is used for mapping api response which's have status and data
 * @param data is response data with generic type of T
 * @param meta is response meta
 * */
data class BaseResponse<T>(
    @SerializedName("data")
    var data: T? = null,
    @SerializedName("page")
    var page: Int? = 0,
    @SerializedName("per_page")
    var perPage: Int? = 0,
    @SerializedName("total")
    var total: Int? = 0,
    @SerializedName("total_pages")
    var totalPages: Int? = 0
)