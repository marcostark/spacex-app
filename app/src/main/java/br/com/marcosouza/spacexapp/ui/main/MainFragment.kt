package br.com.marcosouza.spacexapp.ui.main

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.model.Launch
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.ui.main.state.MainStateEvent
import br.com.marcosouza.spacexapp.util.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import java.util.concurrent.TimeUnit


class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener
    lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Atividade inválida!")

        subscribObservers()
        triggerGetLauchUpcomingEvent()
    }

    private fun subscribObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->
            println("DEBUG: Datasource: {$dataState}")

            dataStateListener.onDataStateChange(dataState)

            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let {mainViewState ->
                    mainViewState.launch?.let {launch ->
                        viewModel.setLaunchData(launch)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.launch?.let {launch ->
                this.initComponents(launch)
            }
        })
    }

    private fun initComponents(launch: Launch) {
        val dateFormated = Utils.toSimpleString(launch.launchDate)
        context?.let { context ->
            Glide.with(context)
                .load(launch.links?.missionPatchSmall)
                .into(image_launch_mission)
        }
        text_launch_date_upcoming.text = dateFormated
        text_launch_title.text = launch.rocket?.rocketName
        text_launch_site_value.text = launch.launchSite?.siteNameLong

        val startDate = Calendar.getInstance()

        val startDateMillis =
            startDate.timeInMillis

        val endDateMillis = launch.launchDateUnix?.times(1000)

        val totalMillis = endDateMillis?.minus(startDateMillis)

        this.countdownToLaunch(totalMillis)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //R.id.action_about -> triggerAboutEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetLauchUpcomingEvent() {
        viewModel.setStateEvent(MainStateEvent.GetNextLaunchEvent())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateListener = context as DataStateListener
        }catch (e: ClassCastException) {
            println("DEBUG: $context must implement DataStateListener")
        }
    }

    private fun countdownToLaunch(totalMillis: Long?){
         this.countDownTimer = object : CountDownTimer(totalMillis!!, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var millisUntilFinished = millisUntilFinished
                val days =
                    TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days)
                val hours =
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours)
                val minutes =
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)
                val seconds =
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                text_launch_count_down.text =
                    "$days days - $hours hours - $minutes minutes - $seconds seconds"
            }

            override fun onFinish() {
                text_launch_count_down.text = "Time to launch!"
            }
        }
        countDownTimer.start()
    }

    override fun onStop() {
        super.onStop()
        println("DEBUG: onStop:")
        this.countDownTimer.cancel()
    }
}
