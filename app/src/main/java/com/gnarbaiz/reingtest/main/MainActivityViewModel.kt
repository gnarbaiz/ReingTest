package com.gnarbaiz.reingtest.main

import androidx.lifecycle.*
import com.gnarbaiz.reingtest.networking.responses.Article
import com.gnarbaiz.reingtest.networking.responses.ArticlesResponse
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val mainActivityModel: MainActivityModel) :
    ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
    }

    fun requestArticles() {
        mainActivityModel.requestArticles()
    }

    fun getRequestArticles() : LiveData<List<Article>> {
       return mainActivityModel.getArticlesResponse()
    }

    fun undoDeleteArticle(article: Article) {
        mainActivityModel.undoDeleteArticle(article)
    }

    fun deleteArticle(article: Article) {
        mainActivityModel.deleteArticle(article)
    }

}