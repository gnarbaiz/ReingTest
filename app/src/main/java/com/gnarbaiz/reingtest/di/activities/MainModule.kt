package com.gnarbaiz.reingtest.di.activities

import com.gnarbaiz.reingtest.SwipeLayoutApp
import com.gnarbaiz.reingtest.main.MainActivity
import com.gnarbaiz.reingtest.main.MainActivityModel
import com.gnarbaiz.reingtest.networking.api.ArticlesApi
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideContext(activity: MainActivity): MainActivity {
        return activity
    }

    @Provides
    fun provideMainActivityModel(app: SwipeLayoutApp, api : ArticlesApi): MainActivityModel {
        return MainActivityModel(api, app)
    }
}