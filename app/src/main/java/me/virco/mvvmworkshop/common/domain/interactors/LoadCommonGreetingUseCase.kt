package me.virco.mvvmworkshop.common.domain.interactors

import io.reactivex.Single
import me.virco.mvvmworkshop.common.domain.model.CommonGreetingRepository
import javax.inject.Inject

class LoadCommonGreetingUseCase @Inject constructor(
        private val greetingRepository: CommonGreetingRepository) : LoadGreetingUseCase {

    override fun execute(): Single<String> = greetingRepository.getGreeting()
}