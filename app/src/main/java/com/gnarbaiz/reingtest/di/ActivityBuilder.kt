package com.gnarbaiz.reingtest.di

import com.gnarbaiz.reingtest.di.activities.MainModule
import com.gnarbaiz.reingtest.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun bindMainActivity(): MainActivity

}