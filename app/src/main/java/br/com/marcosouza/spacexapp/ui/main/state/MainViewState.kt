package br.com.marcosouza.spacexapp.ui.main.state

import br.com.marcosouza.spacexapp.model.*

data class MainViewState(

    var posts: List<Post>? = null,
    var user: User? = null,
    var launch: Launch? = null,
    var rockets: List<Rocket>? = null,
    var launches: List<Launch>? = null,
    var dragons: List<Dragons>? = null
)
