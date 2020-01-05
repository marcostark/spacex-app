package br.com.marcosouza.androidmviarchitecture.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.marcosouza.androidmviarchitecture.api.MyRetrofitBuilder
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainViewState
import br.com.marcosouza.androidmviarchitecture.util.ApiEmptyResponse
import br.com.marcosouza.androidmviarchitecture.util.ApiErrorResponse
import br.com.marcosouza.androidmviarchitecture.util.ApiSuccessResponse
import br.com.marcosouza.androidmviarchitecture.util.DataState

object Repository {

    fun getPosts(): LiveData<DataState<MainViewState>> {
        return Transformations
            .switchMap(MyRetrofitBuilder.apiService.getPosts()){ apiResponse ->
                object: LiveData<DataState<MainViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse){
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = MainViewState(
                                        posts = apiResponse.body
                                    )
                                )
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = apiResponse.errorMessage
                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    message = "HTTP 204. Return Nothing"
                                )
                            }
                        }
                    }
                }
            }
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return Transformations
            .switchMap(MyRetrofitBuilder.apiService.getUser(userId)){ apiResponse ->
                object: LiveData<DataState<MainViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse){
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = MainViewState(
                                        user = apiResponse.body
                                    )
                                )
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message =  apiResponse.errorMessage

                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    message = "HTTP 204. Return Nothing"
                                )
                            }
                        }
                    }
                }
            }
    }

}
