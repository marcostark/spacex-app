package br.com.marcosouza.androidmviarchitecture.repository

import androidx.lifecycle.LiveData
import br.com.marcosouza.androidmviarchitecture.api.MyRetrofitBuilder
import br.com.marcosouza.androidmviarchitecture.model.Post
import br.com.marcosouza.androidmviarchitecture.model.User
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainViewState
import br.com.marcosouza.androidmviarchitecture.util.ApiSuccessResponse
import br.com.marcosouza.androidmviarchitecture.util.DataState
import br.com.marcosouza.androidmviarchitecture.util.GenericApiResponse

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
