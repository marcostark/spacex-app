package br.com.marcosouza.spacexapp.api

import androidx.lifecycle.LiveData
import br.com.marcosouza.spacexapp.model.*
import br.com.marcosouza.spacexapp.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("launches/latest")
    fun getLaunch(
    ): LiveData<GenericApiResponse<Launch>>

    @GET("launches/next")
    fun getLaunchNext(
    ): LiveData<GenericApiResponse<Launch>>

    @GET("launches")
    fun getAllLaunches(
    ): LiveData<GenericApiResponse<List<Launch>>>

    @GET("rockets")
    fun getAllRockets(
    ): LiveData<GenericApiResponse<List<Rocket>>>

    @GET("dragons")
    fun getAllDragons(
    ): LiveData<GenericApiResponse<List<Dragons>>>
}
