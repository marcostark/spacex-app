package br.com.marcosouza.spacexapp.ui.main.fragments.latest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.model.Launch
import br.com.marcosouza.spacexapp.ui.adapter.LaunchListAdapter
import br.com.marcosouza.spacexapp.ui.main.MainViewModel
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent
import br.com.marcosouza.spacexapp.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_latest.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.ClassCastException
import java.lang.Exception

class LatestFragment : Fragment(),
    LaunchListAdapter.Interaction  {

    private lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener
    lateinit var launchListAdapter: LaunchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_latest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Atividade inválida!")

        subscribeObservers()
        initRecyclerView()
        triggerGetLaunchEvent()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("DEBUG: Datasource: {$dataState}")

            dataStateListener.onDataStateChange(dataState)

            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->
                    mainViewState.launches?.let { launches ->
                        viewModel.setLaunchesListData(launches)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.launches?.let {list ->
                println("DEBUG Setting post to recycleview: ${viewState.launches}")
                launchListAdapter.submitList(list)

            }
        })
    }

    private fun initRecyclerView(){
        rv_latest.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoration =
                TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            launchListAdapter =
                LaunchListAdapter(this@LatestFragment)
            adapter = launchListAdapter
        }
    }

    private fun triggerGetLaunchEvent() {
        viewModel.setStateEvent(MainStateEvent.GetAllLaunchesEvent())
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

    override fun onItemSelected(position: Int, item: Launch) {
        TODO("Not yet implemented")
    }
}
