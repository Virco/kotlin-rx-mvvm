package me.virco.mvvmworkshop.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.virco.mvvmworkshop.App
import me.virco.mvvmworkshop.common.domain.model.CommonGreetingRepository
import javax.inject.Singleton

@Module class AppModule {
    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Singleton @Provides
    fun provideCommonHelloService() = CommonGreetingRepository()
}