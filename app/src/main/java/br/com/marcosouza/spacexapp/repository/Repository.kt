package br.com.marcosouza.spacexapp.repository

import androidx.lifecycle.LiveData
import br.com.marcosouza.spacexapp.api.MyRetrofitBuilder
import br.com.marcosouza.spacexapp.model.*
import br.com.marcosouza.spacexapp.ui.main.state.MainViewState
import br.com.marcosouza.spacexapp.util.ApiSuccessResponse
import br.com.marcosouza.spacexapp.util.DataState
import br.com.marcosouza.spacexapp.util.GenericApiResponse

object Repository {

    fun getAllRockets(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<Rocket>, MainViewState>(){
            override fun handleApiSucessResponse(response: ApiSuccessResponse<List<Rocket>>) {
                result.value = DataState.data(
                    data = MainViewState(
                        rockets = response.body
                    )
                )
            }
            override fun createCall(): LiveData<GenericApiResponse<List<Rocket>>> {
                return MyRetrofitBuilder.apiService.getAllRockets()
            }
        }.asLiveData()
    }

    fun getAllDragons(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<Dragons>, MainViewState>(){
            override fun handleApiSucessResponse(response: ApiSuccessResponse<List<Dragons>>) {
                result.value = DataState.data(
                    data = MainViewState(
                        dragons = response.body
                    )
                )
            }
            override fun createCall(): LiveData<GenericApiResponse<List<Dragons>>> {
                return MyRetrofitBuilder.apiService.getAllDragons()
            }
        }.asLiveData()
    }

    fun getAllLaunches(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<Launch>, MainViewState>(){
            override fun handleApiSucessResponse(response: ApiSuccessResponse<List<Launch>>) {
                result.value = DataState.data(
                    data = MainViewState(
                        launches = response.body
                    )
                )
            }
            override fun createCall(): LiveData<GenericApiResponse<List<Launch>>> {
                return MyRetrofitBuilder.apiService.getAllLaunches()
            }
        }.asLiveData()
    }

    fun getLatestLaunch(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<Launch, MainViewState>(){
            override fun handleApiSucessResponse(response: ApiSuccessResponse<Launch>) {
                result.value = DataState.data(
                    data = MainViewState(
                        launch = response.body
                    )
                )
            }
            override fun createCall(): LiveData<GenericApiResponse<Launch>> {
                return MyRetrofitBuilder.apiService.getLaunch()
            }
        }.asLiveData()
    }

    fun getNextLaunch(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<Launch, MainViewState>(){
            override fun handleApiSucessResponse(response: ApiSuccessResponse<Launch>) {
                result.value = DataState.data(
                    data = MainViewState(
                        launch = response.body
                    )
                )
            }
            override fun createCall(): LiveData<GenericApiResponse<Launch>> {
                return MyRetrofitBuilder.apiService.getLaunchNext()
            }
        }.asLiveData()
    }
}
