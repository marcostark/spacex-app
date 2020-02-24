package br.com.marcosouza.spacexapp.ui.main.fragments.rockets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RocketsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is rockets Fragment"
    }
    val text: LiveData<String> = _text
}
