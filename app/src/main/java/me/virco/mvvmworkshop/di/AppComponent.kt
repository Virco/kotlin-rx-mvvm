package me.virco.mvvmworkshop.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.virco.mvvmworkshop.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(app: App): Builder
        fun build(): AppComponent
    }
    fun inject(app: App)
}