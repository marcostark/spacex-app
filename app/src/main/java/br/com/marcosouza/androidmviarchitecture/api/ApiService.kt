package br.com.marcosouza.androidmviarchitecture.api

import br.com.marcosouza.androidmviarchitecture.model.Post
import br.com.marcosouza.androidmviarchitecture.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getPosts(): List<Post>

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): User
}
