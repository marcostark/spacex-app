package br.com.marcosouza.androidmviarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.marcosouza.androidmviarchitecture.model.Post
import br.com.marcosouza.androidmviarchitecture.model.User
import br.com.marcosouza.androidmviarchitecture.repository.Repository
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainStateEvent
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainViewState
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainStateEvent.*
import br.com.marcosouza.androidmviarchitecture.util.AbsentLiveData
import br.com.marcosouza.androidmviarchitecture.util.DataState

class MainViewModel: ViewModel(){

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent){ stateEvent ->

            stateEvent?.let {stateEvent
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        println("DEBUG: New StateEvent detected: $stateEvent")
        when(stateEvent){

            is GetBlogPostsEvent -> {
                return Repository.getPosts()
            }

            is GetUserEvent -> {
                return Repository.getUser(stateEvent.userId)
            }

            is None ->{
                return AbsentLiveData.create()
            }
        }
    }

    fun setPostsListData(posts: List<Post>){
        val update = getCurrentViewStateOrNew()
        update.posts = posts
        _viewState.value = update
    }

    fun setUser(user: User){
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState{
        val value = viewState.value?.let {
            it
        }?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent){
        _stateEvent.value = event
    }

}
