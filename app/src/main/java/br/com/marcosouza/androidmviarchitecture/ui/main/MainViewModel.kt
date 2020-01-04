package br.com.marcosouza.androidmviarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.marcosouza.androidmviarchitecture.ui.main.state.MainViewState

class MainViewModel: ViewModel(){

    private val _viewModel: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewModel
}
