package me.virco.mvvmworkshop.lobby

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import me.virco.mvvmworkshop.common.domain.interactors.LoadCommonGreetingUseCase
import me.virco.mvvmworkshop.common.domain.interactors.LoadGreetingUseCase
import me.virco.mvvmworkshop.common.domain.interactors.LoadLobbyGreetingUseCase
import me.virco.mvvmworkshop.common.viewmodel.Failure
import me.virco.mvvmworkshop.common.viewmodel.Loading
import me.virco.mvvmworkshop.common.viewmodel.Response
import me.virco.mvvmworkshop.common.viewmodel.Success
import me.virco.mvvmworkshop.rx.SchedulersFacade



class LobbyViewModel (
    private val loadCommonGreetingUseCase: LoadCommonGreetingUseCase,
    private val loadLobbyGreetingUseCase: LoadLobbyGreetingUseCase,
    private val schedulersFacade: SchedulersFacade)
    : ViewModel() {

    private val disposables = CompositeDisposable()

    val response = MutableLiveData<Response<String>>()

    override fun onCleared() {
        disposables.clear()
    }

    fun loadCommonGreeting() = loadGreeting(loadCommonGreetingUseCase)

    fun loadLobbyGreeting() = loadGreeting(loadLobbyGreetingUseCase)

    private fun loadGreeting(loadGreetingUseCase: LoadGreetingUseCase) {
        disposables.add(loadGreetingUseCase.execute()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe { response.value = Loading() }
                .subscribeBy(
                        onSuccess = { response.value = Success(it) },
                        onError = { response.value = Failure(it) }
                ))
    }
}