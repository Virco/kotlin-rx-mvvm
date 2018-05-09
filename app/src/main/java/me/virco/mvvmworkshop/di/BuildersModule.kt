package me.virco.mvvmworkshop.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.virco.mvvmworkshop.lobby.LobbyActivity
import me.virco.mvvmworkshop.lobby.LobbyModule

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [LobbyModule::class])
    abstract fun bindLobbyActivity(): LobbyActivity
}