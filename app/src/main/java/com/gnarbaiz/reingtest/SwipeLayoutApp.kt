package com.gnarbaiz.reingtest

import android.app.Activity
import android.app.Application
import com.gnarbaiz.reingtest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SwipeLayoutApp : Application(), HasActivityInjector {

    @set:Inject
    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector!!
    }
}