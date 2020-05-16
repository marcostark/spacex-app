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
import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.model.Launch
import br.com.marcosouza.spacexapp.ui.adapter.LatestLaunchListAdapter
import br.com.marcosouza.spacexapp.model.Post
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent
import br.com.marcosouza.spacexapp.util.Utils
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
        triggerGetLauchUpcomingEvent()
    }

    private fun subscribObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->
            println("DEBUG: Datasource: {$dataState}")

            // handle loading and message
            dataStateListener.onDataStateChange(dataState)

            // Handle Data<T>
            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let {mainViewState ->
                    mainViewState.launch?.let {launch ->
                        viewModel.setLaunchData(launch)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.launch?.let {launch ->
                println("DEBUG Setting post to recycleview: ${launch}")
                this.initComponents(launch)
            }
        })
    }

    private fun initComponents(launch: Launch) {
        var dateFormated = Utils.toSimpleString(launch.launchDate)
        text_launch_date_upcoming.text = dateFormated
        text_launch_title.text = launch.rocket?.rocketName
        text_launch_site_value.text = launch.launchSite?.siteNameLong
    }

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

    private fun triggerGetLauchUpcomingEvent() {
        viewModel.setStateEvent(MainStateEvent.GetNextLaunchEvent())
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
