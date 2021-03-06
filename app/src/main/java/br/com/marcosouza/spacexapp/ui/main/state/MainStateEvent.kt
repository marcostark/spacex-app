package br.com.marcosouza.spacexapp.ui.main.state

// see: https://kotlinlang.org/docs/reference/sealed-classes.html
sealed class MainStateEvent {

    class GetRocketsEvent: MainStateEvent()

    class GetDragonsEvent: MainStateEvent()

    class GetAllLaunchesEvent: MainStateEvent()

    class GetLaunchEvent: MainStateEvent()

    class GetNextLaunchEvent: MainStateEvent()

    class None: MainStateEvent()

}
