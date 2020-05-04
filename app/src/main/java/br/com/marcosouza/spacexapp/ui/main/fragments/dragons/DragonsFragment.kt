package br.com.marcosouza.spacexapp.ui.main.fragments.dragons

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.ui.main.MainViewModel
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent
import java.lang.ClassCastException
import java.lang.Exception

class DragonsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dragons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Atividade inválida!")

        subscribeObservers()
        triggerGetDragonsEvent()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("DEBUG: Datasource: {$dataState}")

            dataStateListener.onDataStateChange(dataState)

            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->
                    mainViewState.dragons?.let { dragons ->
                        viewModel.setDragonsListData(dragons)
                    }
                }
            }
        })
    }

    private fun triggerGetDragonsEvent() {
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
}
