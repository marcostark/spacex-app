package br.com.marcosouza.spacexapp.ui.main.state

import br.com.marcosouza.spacexapp.model.*

data class MainViewState(

    var launch: Launch? = null,
    var rockets: List<Rocket>? = null,
    var launches: List<Launch>? = null,
    var dragons: List<Dragons>? = null
)
