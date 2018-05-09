package me.virco.mvvmworkshop.common.domain.model

import io.reactivex.Single

class CommonGreetingRepository {
    fun getGreeting() = Single.just("Hello from CommonGreetingsRepository")
}