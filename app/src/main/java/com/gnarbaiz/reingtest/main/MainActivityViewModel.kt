package com.gnarbaiz.reingtest.main

import androidx.lifecycle.*
import com.gnarbaiz.reingtest.networking.responses.ArticlesResponse
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val mainActivityModel: MainActivityModel) :
    ViewModel(), LifecycleObserver {

    var articlesResponse: MutableLiveData<ArticlesResponse> = MutableLiveData()


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
    }

    fun requestArticles() {
        mainActivityModel.requestArticles()
    }

    fun getRequestArticles() : LiveData<ArticlesResponse> {
       return mainActivityModel.getArticlesResponse()
    }

}