package br.com.marcosouza.spacexapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.model.Launch
import br.com.marcosouza.spacexapp.ui.main.fragments.latest.LatestFragment
import br.com.marcosouza.spacexapp.ui.main.fragments.rockets.RocketsFragment
import br.com.marcosouza.spacexapp.ui.main.state.DataStateListener
import br.com.marcosouza.spacexapp.util.DataState
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    DataStateListener, ICallbackListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        showFragment(MainFragment())
    }
    private fun showFragment(fragment: Fragment) {
      supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                fragment, "fragment")
            .commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        when(fragment) {
            is LatestFragment -> fragment.setOnLaunchDetailClickListener(this)
        }
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {
            // handle loading
            showProgressBar(it.loading)

            //handle message
            it.message?.let{message ->
                message.getContentIfNotHandled()?.let {message ->
                    showToast(message)
                }
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar(isVisible: Boolean){
        if(isVisible){
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private var navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
//                R.id.navigation_dragons -> {
//                    showFragment(DragonsFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
                R.id.navigation_rockets -> {
                    showFragment(RocketsFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_latest -> {
                    showFragment(LatestFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_home -> {
                    showFragment(MainFragment())
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

    override fun onCallBackLaunchDetails(launch: Launch) {
        println("DEBUG: ITEM: $launch")
        viewModel.select(launch)
        this.showFragment(LaunchDetailsFragment())
    }
}
