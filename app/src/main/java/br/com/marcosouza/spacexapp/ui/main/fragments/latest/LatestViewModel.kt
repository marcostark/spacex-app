package br.com.marcosouza.spacexapp.ui.main.fragments.latest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LatestViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is latest Fragment"
    }
    val text: LiveData<String> = _text
}
