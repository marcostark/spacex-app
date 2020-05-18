package br.com.marcosouza.spacexapp.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.model.Launch
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.util.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_launch_details.*
import java.lang.ClassCastException
import java.lang.Exception

class LaunchDetailsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Atividade inválida!")

        subscribeObservers()
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

    private fun subscribeObservers(){
        viewModel.seletedLaunch.observe(viewLifecycleOwner, Observer<Launch> { launch ->

            context?.let { Glide.with(it).load(launch.links?.missionPatchSmall).into(image_detail_launch) }
            text_launch_rocket.text = launch.rocket?.rocketName
            text_details_launch_date_upcoming.text = launch.missionName
            text_details_launch_date_upcoming.text = Utils.toSimpleString(launch.launchDate)
            text_launch_site_value.text = launch.launchSite?.siteName
            if (launch.launchSuccess == true) {
                text_details_launch_status.text = "Sucess"
            } else {
                text_details_launch_status.text = "Fail"
            }
            text_details_launch.text = launch.details
            // summay
            // version
            text_details_launch_firt_stage.text =
                "Cores: " + launch.rocket?.firstStage?.cores?.size.toString()
            text_details_launch_second_stage.text = launch.rocket?.secondStage?.payloads?.get(0)?.massKg.toString()

            // Abrir links
            text_details_launch_firt_youtube.setOnClickListener {
                val url = launch.links?.videoLink
                if(!url.isNullOrBlank()){
                    println("DEBUG: Video Youtube: ${url}")
                    this.onBrowser(url)
                }

            }

            text_details_launch_reddit.setOnClickListener {
                val url = launch.links?.redditLaunch
                if(!url.isNullOrBlank()){
                    println("DEBUG: Video Youtube: ${url}")
                    this.onBrowser(url)
                }
            }
        })
    }

    private fun onBrowser(url: String?) {
        var urlFormated = ""

        if (!url?.startsWith("https://")!! && !url.startsWith("http://")) {
            urlFormated = "https://$url"
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlFormated))
        startActivity(Intent.createChooser(intent, "Browse with"))
    }
}
