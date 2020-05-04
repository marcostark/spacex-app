package br.com.marcosouza.spacexapp.ui.main.state


// see: https://kotlinlang.org/docs/reference/sealed-classes.html
sealed class MainStateEvent {

    class GetBlogPostsEvent: MainStateEvent()

    class GetUserEvent(
        val userId: String
    ): MainStateEvent()

    class GetRocketsEvent: MainStateEvent()

    class GetDragonsEvent: MainStateEvent()

    class GetAllLaunchesEvent: MainStateEvent()

    class GetLaunchEvent: MainStateEvent()

    class None: MainStateEvent()

}
