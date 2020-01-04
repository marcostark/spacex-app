package br.com.marcosouza.androidmviarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.marcosouza.androidmviarchitecture.model.Post
import br.com.marcosouza.androidmviarchitecture.model.User
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainStateEvent
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainViewState
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainStateEvent.*
import br.com.marcosouza.androidmviarchitecture.util.AbsentLiveData

class MainViewModel: ViewModel(){

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){ stateEvent ->

            stateEvent?.let {stateEvent
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        when(stateEvent) {

            is GetBlogPostsEvent -> {
                //return AbsentLiveData.create()
                return object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val blogList: ArrayList<Post> = ArrayList()
                        blogList.add(
                            Post(
                                pk = 0,
                                title = "Vancouver PNE 2019",
                                body = "Here is Jess and I at the Vancouver PNE. We ate a lot of food.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.jpg"
                            )
                        )
                        blogList.add(
                            Post(
                                pk = 1,
                                title = "Ready for a Walk",
                                body = "Here I am at the park with my dogs Kiba and Maizy. Maizy is the smaller one and Kiba is the larger one.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image2.jpg"
                            )
                        )
                        value = MainViewState(
                            posts = blogList
                        )
                    }
                }

            }

            is GetUserEvent -> {
                //return AbsentLiveData.create()
                return object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val user = User(
                            email = "mitch@tabian.ca",
                            username = "mitch",
                            image = "https://cdn.open-api.xyz/open-api-static/static-random-images/logo_1080_1080.png"
                        )
                        value = MainViewState(
                            user = user
                        )
                    }
                }
            }

            is None -> {
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
