package br.com.marcosouza.androidmviarchitecture.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.marcosouza.androidmviarchitecture.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment()
    }
    fun showFragment() {
      supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MainFragment(), "MainFragment(")
            .commit()
    }
}
