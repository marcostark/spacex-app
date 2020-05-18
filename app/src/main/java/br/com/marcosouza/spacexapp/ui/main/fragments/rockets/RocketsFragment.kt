package br.com.marcosouza.spacexapp.ui.main.fragments.rockets

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
import br.com.marcosouza.spacexapp.model.Rocket
import br.com.marcosouza.spacexapp.ui.adapter.RocketsListAdapter
import br.com.marcosouza.spacexapp.ui.main.MainViewModel
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent
import br.com.marcosouza.spacexapp.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_rockets.*
import java.lang.ClassCastException
import java.lang.Exception

class RocketsFragment : Fragment(),
    RocketsListAdapter.Interaction {

    private lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener
    lateinit var rocketsListAdapter: RocketsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rockets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Atividade inválida!")

        subscribeObservers()
        initRecyclerView()
        triggerGetRocketsEvent()

    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("DEBUG: Datasource: {$dataState}")

            dataStateListener.onDataStateChange(dataState)

            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->
                    mainViewState.rockets?.let { rockets ->
                        viewModel.setRocketsListData(rockets)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.rockets?.let {list ->
                println("DEBUG Setting post to recycleview: ${viewState.rockets}")
                rocketsListAdapter.submitList(list)

            }
        })
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoration =
                TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            rocketsListAdapter =
                RocketsListAdapter(this@RocketsFragment)
            adapter = rocketsListAdapter
        }
    }

    private fun triggerGetRocketsEvent() {
        viewModel.setStateEvent(MainStateEvent.GetRocketsEvent())
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

    override fun onItemSelected(position: Int, item: Rocket) {
        println("DEBUG: ITEM: $item")
    }
}
