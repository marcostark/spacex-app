package br.com.marcosouza.spacexapp.api

import androidx.lifecycle.LiveData
import br.com.marcosouza.spacexapp.model.*
import br.com.marcosouza.spacexapp.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getPosts(): LiveData<GenericApiResponse<List<Post>>>

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<User>>

    @GET("launches/latest")
    fun getLatestLauch(
    ): LiveData<GenericApiResponse<Launch>>

    @GET("rockets")
    fun getAllRockets(
    ): LiveData<GenericApiResponse<Rocket>>

    @GET("dragons")
    fun getAllDragons(
    ): LiveData<GenericApiResponse<Dragons>>
}
