package br.com.marcosouza.spacexapp.ui.main.fragments.dragons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DragonsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dragons Fragment"
    }
    val text: LiveData<String> = _text
}
