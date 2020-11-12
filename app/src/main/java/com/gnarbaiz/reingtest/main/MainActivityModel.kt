package com.gnarbaiz.reingtest.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.gnarbaiz.reingtest.db.AppDatabase
import com.gnarbaiz.reingtest.networking.api.ArticlesApi
import com.gnarbaiz.reingtest.networking.responses.Article
import com.gnarbaiz.reingtest.networking.responses.ArticlesResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityModel @Inject constructor(api: ArticlesApi, context: Context) {

    private val db: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, AppDatabase.dbName
    ).build()

    private var articlesList = MutableLiveData<List<Article>>()
    var responseError = MutableLiveData<String>()
    private var articlesApi: ArticlesApi = api

    fun requestArticles() {
        val request = articlesApi.requestArticles("android")
        request.enqueue(object :
            Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                if (response.isSuccessful) {
                    saveArticle(response.body()!!)
                    return
                }
                responseError.postValue("Error")
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                responseError.postValue("Error")
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun getArticlesResponse(): LiveData<List<Article>> {
        return articlesList
    }

    fun getErrorMessage(): LiveData<String> {
        return responseError
    }

    fun saveArticle(articlesResponse: ArticlesResponse) {
        GlobalScope.launch {
            articlesResponse.articlesList.forEach {
                db.articlesDao().insert(it)
            }
            articlesList.postValue(db.articlesDao().getAllVisible())
        }
    }

    fun undoDeleteArticle (article: Article) {
        GlobalScope.launch {
            db.articlesDao().update(true, article.objectID)
        }
    }

    fun deleteArticle (article: Article) {
        GlobalScope.launch {
            db.articlesDao().update(false, article.objectID)
        }
    }

    companion object {
        private const val TAG = "MainActivityModel"
    }
}