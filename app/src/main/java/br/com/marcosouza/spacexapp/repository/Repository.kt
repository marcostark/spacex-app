package br.com.marcosouza.spacexapp.repository

import androidx.lifecycle.LiveData
import br.com.marcosouza.spacexapp.api.MyRetrofitBuilder
import br.com.marcosouza.spacexapp.model.Post
import br.com.marcosouza.spacexapp.model.User
import br.com.marcosouza.spacexapp.ui.main.state.MainViewState
import br.com.marcosouza.spacexapp.util.ApiSuccessResponse
import br.com.marcosouza.spacexapp.util.DataState
import br.com.marcosouza.spacexapp.util.GenericApiResponse

object Repository {

    fun getPosts(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<Post>, MainViewState>(){
            override fun handleApiSucessResponse(response: ApiSuccessResponse<List<Post>>) {
                result.value = DataState.data(
                    data = MainViewState(
                        posts = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Post>>> {
                return MyRetrofitBuilder.apiService.getPosts()
            }
        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<User, MainViewState>(){
            override fun handleApiSucessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(
                    data = MainViewState(
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return MyRetrofitBuilder.apiService.getUser(userId)
            }
        }.asLiveData()
    }

}
