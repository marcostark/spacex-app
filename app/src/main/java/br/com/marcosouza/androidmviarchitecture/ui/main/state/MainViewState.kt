package br.com.marcosouza.androidmviarchitecture.ui.main.state

import br.com.marcosouza.androidmviarchitecture.model.Post
import br.com.marcosouza.androidmviarchitecture.model.User

data class MainViewState(

    var posts: List<Post>? = null,
    var user: User? = null

)
