package br.com.marcosouza.androidmviarchitecture.ui.main.state


// see: https://kotlinlang.org/docs/reference/sealed-classes.html
sealed class MainStateEvent {

    class GetBlogPostsEvent: MainStateEvent()

    class GetUserEvent(
        val userId: String
    ): MainStateEvent()

    class None: MainStateEvent()

}
