package br.com.marcosouza.spacexapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.marcosouza.spacexapp.model.*
import br.com.marcosouza.spacexapp.repository.Repository
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent
import br.com.marcosouza.spacexapp.ui.main.state.MainViewState
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent.*
import br.com.marcosouza.spacexapp.util.AbsentLiveData
import br.com.marcosouza.spacexapp.util.DataState

class MainViewModel: ViewModel(){

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val seletedLaunch = MutableLiveData<Launch>()

    fun select(launch: Launch) {
        seletedLaunch.value =  launch
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is rockets Fragment"
    }
    val text: LiveData<String> = _text

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

            is GetRocketsEvent -> {
                return Repository.getAllRockets()
            }

            is GetDragonsEvent -> {
                return Repository.getAllDragons()
            }

            is GetAllLaunchesEvent -> {
                return Repository.getAllLaunches()
            }

            is GetLaunchEvent -> {
                return Repository.getLatestLaunch()
            }

            is GetNextLaunchEvent -> {
                return Repository.getNextLaunch()
            }

            is None ->{
                return AbsentLiveData.create()
            }
        }
    }

    fun setRocketsListData(rockets: List<Rocket>){
        val update = getCurrentViewStateOrNew()
        update.rockets = rockets
        _viewState.value = update
    }

    fun setDragonsListData(dragons: List<Dragons>){
        val update = getCurrentViewStateOrNew()
        update.dragons = dragons
        _viewState.value = update
    }

    fun setLaunchesListData(launches: List<Launch>){
        val update = getCurrentViewStateOrNew()
        update.launches = launches
        _viewState.value = update
    }

    fun setLaunchData(launch: Launch){
        val update = getCurrentViewStateOrNew()
        update.launch = launch
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value?.let {
            it
        }?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent){
        _stateEvent.value = event
    }

}
