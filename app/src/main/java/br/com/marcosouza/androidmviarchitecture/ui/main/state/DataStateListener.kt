package br.com.marcosouza.androidmviarchitecture.ui.main.state

import br.com.marcosouza.androidmviarchitecture.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)

}
