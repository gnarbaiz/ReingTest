package com.gnarbaiz.reingtest.di

import android.app.Application
import com.gnarbaiz.reingtest.SwipeLayoutApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: SwipeLayoutApp): Application {
        return application
    }
}