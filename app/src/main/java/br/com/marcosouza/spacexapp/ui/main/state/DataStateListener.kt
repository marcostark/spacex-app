package br.com.marcosouza.spacexapp.ui.main.state

import br.com.marcosouza.spacexapp.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)

}
