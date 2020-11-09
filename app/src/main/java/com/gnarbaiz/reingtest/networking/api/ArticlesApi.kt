package com.gnarbaiz.reingtest.networking.api

import com.gnarbaiz.reingtest.networking.responses.ArticlesResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query


interface ArticlesApi {

    @POST("v1/search_by_date")
    fun requestArticles(@Query("query") query: String): Call<ArticlesResponse>

}