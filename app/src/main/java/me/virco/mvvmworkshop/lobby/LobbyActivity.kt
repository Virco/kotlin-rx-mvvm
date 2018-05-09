package me.virco.mvvmworkshop.lobby

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import me.virco.mvvmworkshop.R
import javax.inject.Inject

import me.virco.mvvmworkshop.common.viewmodel.Failure
import me.virco.mvvmworkshop.common.viewmodel.Loading
import me.virco.mvvmworkshop.common.viewmodel.Response
import me.virco.mvvmworkshop.common.viewmodel.Success

import kotlinx.android.synthetic.main.activity_lobby.*

class LobbyActivity : AppCompatActivity() {

    companion object {
        val TAG: String = LobbyActivity::class.java.simpleName
    }

    @Inject
    lateinit var viewModelFactory: LobbyViewModelFactory
    private lateinit var viewModel: LobbyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(LobbyViewModel::class.java)

        viewModel.response.observe(this, processResponse)

        commonGreetingButton.setOnClickListener { viewModel.loadCommonGreeting() }
        lobbyGreetingButton.setOnClickListener { viewModel.loadLobbyGreeting() }

    }

    private val processResponse = Observer<Response<String>> { response ->
        when (response) {
            is Loading -> renderLoadingState()
            is Success -> renderDataState(response.data)
            is Failure -> renderErrorState(response.throwable)
        }
    }

    private fun renderLoadingState() {
        greetingTextView.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
    }

    private fun renderDataState(greeting: String?) {
        loadingIndicator.visibility = View.GONE
        greetingTextView.visibility = View.VISIBLE
        greetingTextView.text = greeting
    }

    private fun renderErrorState(t: Throwable) {
        Log.e(TAG, t.localizedMessage, t)
        loadingIndicator.visibility = View.GONE
        greetingTextView.visibility = View.GONE
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}
