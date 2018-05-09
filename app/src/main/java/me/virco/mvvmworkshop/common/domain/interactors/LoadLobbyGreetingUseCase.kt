package me.virco.mvvmworkshop.common.domain.interactors

import io.reactivex.Single
import me.virco.mvvmworkshop.lobby.LobbyGreetingRepository
import javax.inject.Inject

class LoadLobbyGreetingUseCase @Inject constructor(
        private val greetingRepository: LobbyGreetingRepository) : LoadGreetingUseCase{

     override fun execute(): Single<String> = greetingRepository.getGreeting()
}