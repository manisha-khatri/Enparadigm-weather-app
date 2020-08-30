package com.example.enparadigmweatherapp.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.enparadigmweatherapp.R
import com.example.enparadigmweatherapp.model.WeatherData
import com.example.enparadigmweatherapp.view.state.ErrorState
import com.example.enparadigmweatherapp.view.state.ProgressState
import com.example.enparadigmweatherapp.view.state.SuccessState
import com.example.enparadigmweatherapp.view.state.ViewState
import com.example.enparadigmweatherapp.viewmodel.WeatherDataVM
import kotlinx.android.synthetic.main.activity_main.*


class WeatherDashboardActivity : AppCompatActivity() {

    var weatherDataVM: WeatherDataVM ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObserver()
        initData()
    }

    private fun initObserver() {
        weatherDataVM = ViewModelProviders.of(this).get(WeatherDataVM::class.java)
        weatherDataVM!!.getViewState().observe(this,  Observer<ViewState>(){
            fun onChanged(viewState: ViewState) {
                if (viewState is ErrorState) {
                    setViewVisibility(true,false,false)

                } else if (viewState is ProgressState) {
                    setViewVisibility(false,true,false)

                } else if (viewState is SuccessState) {
                    setViewVisibility(false,false,true)
                    val weatherData: WeatherData = viewState.weatherData
                }
            }
        })
    }

    fun setViewVisibility(ErrorState:Boolean, ProgressState:Boolean, SuccessState:Boolean){
        errorTV.visibility = if(ErrorState==true) View.VISIBLE else View.GONE
        progressBar.visibility = if(ProgressState==true) View.VISIBLE else View.GONE
        weatherDataTV.visibility = if(SuccessState==true) View.VISIBLE else View.GONE
    }

    private fun initData() {
        weatherDataVM!!.loadWeatherData()
    }

}