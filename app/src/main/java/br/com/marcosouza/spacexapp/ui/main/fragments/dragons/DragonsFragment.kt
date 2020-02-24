package br.com.marcosouza.spacexapp.ui.main.fragments.dragons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.marcosouza.spacexapp.R

class DragonsFragment : Fragment() {

    private lateinit var dragonsViewModel: DragonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dragonsViewModel =
            ViewModelProviders.of(this).get(DragonsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dragons, container, false)
        val textView: TextView = root.findViewById(R.id.text_dragons)
        dragonsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}
