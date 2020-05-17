package br.com.marcosouza.spacexapp.ui.main

import br.com.marcosouza.spacexapp.model.Launch

interface ICallbackListener {

    fun onCallBackLaunchDetails(launch: Launch)
}
