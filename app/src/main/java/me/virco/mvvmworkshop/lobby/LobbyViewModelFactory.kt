package me.virco.mvvmworkshop.lobby

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import me.virco.mvvmworkshop.common.domain.interactors.LoadCommonGreetingUseCase
import me.virco.mvvmworkshop.common.domain.interactors.LoadLobbyGreetingUseCase
import me.virco.mvvmworkshop.rx.SchedulersFacade

class LobbyViewModelFactory(
        private val loadCommonGreetingUseCase: LoadCommonGreetingUseCase,
        private val loadLobbyGreetingUseCase: LoadLobbyGreetingUseCase,
        private val schedulersFacade: SchedulersFacade): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LobbyViewModel::class.java)) {
            return LobbyViewModel(loadCommonGreetingUseCase = loadCommonGreetingUseCase,
                    loadLobbyGreetingUseCase = loadLobbyGreetingUseCase,
                    schedulersFacade = schedulersFacade) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}