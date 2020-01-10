package br.com.marcosouza.spacexapp.ui.main.state

import br.com.marcosouza.spacexapp.model.Post
import br.com.marcosouza.spacexapp.model.User

data class MainViewState(

    var posts: List<Post>? = null,
    var user: User? = null

)
