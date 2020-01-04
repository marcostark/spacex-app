package br.com.marcosouza.androidmviarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainStateEvent
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainViewState
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainStateEvent.*
import br.com.marcosouza.androidmviarchitecture.util.AbsentLiveData

class MainViewModel: ViewModel(){

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewModel: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewModel

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){ stateEvent ->

            stateEvent?.let {stateEvent
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        when(stateEvent) {

            is GetBlogPostsEvent -> {
                return AbsentLiveData.create()

            }

            is GetUserEvent -> {
                return AbsentLiveData.create()
            }

            is None -> {
                return AbsentLiveData.create()
            }
        }
    }
}
