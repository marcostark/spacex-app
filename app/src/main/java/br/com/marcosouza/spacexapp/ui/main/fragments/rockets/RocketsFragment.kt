package br.com.marcosouza.spacexapp.ui.main.fragments.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.marcosouza.spacexapp.R

class RocketsFragment : Fragment() {

    private lateinit var rocketsViewModel: RocketsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rocketsViewModel =
            ViewModelProviders.of(this).get(RocketsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rockets, container, false)
        val textView: TextView = root.findViewById(R.id.text_rockets)
        rocketsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}
