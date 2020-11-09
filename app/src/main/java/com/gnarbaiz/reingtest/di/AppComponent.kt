package com.gnarbaiz.reingtest.di

import com.gnarbaiz.reingtest.SwipeLayoutApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SwipeLayoutApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: SwipeLayoutApp)

}