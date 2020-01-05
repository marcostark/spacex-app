package br.com.marcosouza.androidmviarchitecture.api

import androidx.lifecycle.LiveData
import br.com.marcosouza.androidmviarchitecture.model.Post
import br.com.marcosouza.androidmviarchitecture.model.User
import br.com.marcosouza.androidmviarchitecture.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getPosts(): LiveData<GenericApiResponse<List<Post>>>

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<User>>
}
