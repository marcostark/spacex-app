package br.com.marcosouza.androidmviarchitecture.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.marcosouza.androidmviarchitecture.R
import br.com.marcosouza.androidmviarchitecture.ui.main.state.DataStateListener
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainStateEvent
import java.lang.ClassCastException
import java.lang.Exception

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener

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
    }

    //
    fun subscribObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->
            println("DEBUG: Datasource: {$dataState}")

            // handle loading and message
            dataStateListener.onDataStateChange(dataState)

            // Handle Data<T>
            dataState.data?.let { mainViewState ->
                mainViewState.posts?.let {posts ->
                    // set Posts data
                    viewModel.setPostsListData(posts)
                }

                mainViewState.user?.let {user ->
                    // set Users Data
                    viewModel.setUser(user)
                }
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.posts?.let {
                println("DEBUG Setting post to recycleview: ${it}")
            }

            viewState.user?.let {
                println("DEBUG Setting user data: ${it}")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_get_user -> triggerGetUserEvent()
            R.id.action_get_blogs -> triggerGetPostsEvent()
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
}
