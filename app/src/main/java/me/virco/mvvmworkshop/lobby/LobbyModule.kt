package me.virco.mvvmworkshop.lobby

import dagger.Module
import dagger.Provides
import me.virco.mvvmworkshop.common.domain.interactors.LoadCommonGreetingUseCase
import me.virco.mvvmworkshop.common.domain.interactors.LoadLobbyGreetingUseCase
import me.virco.mvvmworkshop.rx.SchedulersFacade

@Module
class LobbyModule {

    @Provides
    fun provideLobbyGreetingRepository() = LobbyGreetingRepository()

    @Provides
    fun provideLobbyViewModelFactory(loadCommonGreetingUseCase: LoadCommonGreetingUseCase,
                                     loadLobbyGreetingUseCase: LoadLobbyGreetingUseCase,
                                     schedulersFacade: SchedulersFacade)
            = LobbyViewModelFactory(loadCommonGreetingUseCase, loadLobbyGreetingUseCase, schedulersFacade)
}