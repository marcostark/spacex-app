package br.com.marcosouza.spacexapp.ui.main.fragments.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.marcosouza.spacexapp.R

class LatestFragment : Fragment() {

    private lateinit var latestViewModel: LatestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        latestViewModel =
            ViewModelProvider(this).get(LatestViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_latest, container, false)
        val textView: TextView = root.findViewById(R.id.text_latest)
        latestViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
