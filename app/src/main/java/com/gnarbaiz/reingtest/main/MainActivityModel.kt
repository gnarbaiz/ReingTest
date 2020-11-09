package com.gnarbaiz.reingtest.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gnarbaiz.reingtest.networking.api.ArticlesApi
import com.gnarbaiz.reingtest.networking.requests.ArticlesRequest
import com.gnarbaiz.reingtest.networking.responses.ArticlesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityModel @Inject constructor(api: ArticlesApi, context: Context) {

//    val db: AppDatabase = Room.databaseBuilder(
//        context.applicationContext,
//        AppDatabase::class.java, AppDatabase.dbName
//    ).build()

    var articlesResponse = MutableLiveData<ArticlesResponse>()
    var responseError = MutableLiveData<String>()
    private var dbCompleted = MutableLiveData<Boolean>()
    private var articlesApi: ArticlesApi = api

    fun requestArticles() {
        val request = articlesApi.requestArticles("android")
        request.enqueue(object :
            Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {

                if (response.isSuccessful) {
                    articlesResponse.postValue(response.body()!!)
                    return
                }
                responseError.postValue("Error")
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                responseError.postValue("Error")
            }
        })
    }

    fun getDBCompleted(): LiveData<Boolean> {
        return dbCompleted
    }

    fun getArticlesResponse(): LiveData<ArticlesResponse> {
        return articlesResponse
    }

    fun getErrorMessage(): LiveData<String> {
        return responseError
    }

    /**
    Saves logged in professional profile.
     */
    suspend fun saveProfile(loginResponse: ArticlesResponse, context: Context) {
//        withContext(Dispatchers.IO) {
////                db.professionalDao().insert(loginResponse.profile)
////        }
    }

    companion object {
        private const val TAG = "MainActivityModel"
    }
}