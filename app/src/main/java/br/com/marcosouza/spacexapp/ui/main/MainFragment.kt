package br.com.marcosouza.spacexapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.ui.adapter.LatestLaunchListAdapter
import br.com.marcosouza.spacexapp.model.Post
import br.com.marcosouza.spacexapp.model.User
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent
import br.com.marcosouza.spacexapp.util.TopSpacingItemDecoration
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.ClassCastException
import java.lang.Exception

class MainFragment : Fragment(),
    LatestLaunchListAdapter.Interaction {

    lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener
    lateinit var latestLaunchListAdapter: LatestLaunchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Atividade inválida!")

        subscribObservers()
        initRecyclerView()
        //triggerGetPostsEvent()
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoration =
                TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            latestLaunchListAdapter =
                LatestLaunchListAdapter(this@MainFragment)
            adapter = latestLaunchListAdapter
        }
    }

    //
    private fun subscribObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->
            println("DEBUG: Datasource: {$dataState}")

            // handle loading and message
            dataStateListener.onDataStateChange(dataState)

            // Handle Data<T>
            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let {mainViewState ->
                    mainViewState.posts?.let {posts ->
                        // set Posts data
                        viewModel.setPostsListData(posts)
                    }

                    mainViewState.user?.let {user ->
                        // set Users Data
                        viewModel.setUser(user)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.posts?.let {list ->
                println("DEBUG Setting post to recycleview: ${viewState.posts}")
                latestLaunchListAdapter.submitList(list)
            }

            viewState.user?.let {
                println("DEBUG Setting user data: ${viewState.user}")
             //   setUserProperties(it)
            }
        })
    }

//    fun setUserProperties(user: User){
//        email.setText(user.email)
//        username.setText(user.username)
//        view?.let {
//            Glide.with(it.context)
//                .load(user.image)
//                .into(image)
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //R.id.action_get_user -> triggerGetUserEvent()
            //R.id.action_get_blogs -> triggerGetPostsEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetPostsEvent() {
        viewModel.setStateEvent(MainStateEvent.GetBlogPostsEvent())
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
    }

    // Caso metodo nao seja adicionado na classe que implementa a interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateListener = context as DataStateListener
        }catch (e: ClassCastException) {
            println("DEBUG: $context must implement DataStateListener")
        }
    }

    override fun onItemSelected(position: Int, item: Post) {
        println("DEBUG: ITEM: $item")
    }
}
