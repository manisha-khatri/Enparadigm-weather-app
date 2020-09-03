package com.example.enparadigmweatherapp.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.enparadigmweatherapp.R
import com.example.enparadigmweatherapp.model.WeatherData
import com.example.enparadigmweatherapp.util.Constants
import com.example.enparadigmweatherapp.view.state.ErrorState
import com.example.enparadigmweatherapp.view.state.ProgressState
import com.example.enparadigmweatherapp.view.state.SuccessState
import com.example.enparadigmweatherapp.viewmodel.WeatherDataVM
import kotlinx.android.synthetic.main.activity_weather_details.*

class WeatherDetailsActivity : AppCompatActivity() {
    var weatherDataVM: WeatherDataVM ?= null
    var cityName: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        initViews()
        initObserver()
        initData()
    }

    fun initViews(){
        if (intent.hasExtra(Constants.CITY_NAME)) {
            cityName = intent.getStringExtra(Constants.CITY_NAME)
            cityName = cityName!!.toLowerCase()
        }
    }

    private fun initObserver() {
        weatherDataVM = ViewModelProviders.of(this).get(WeatherDataVM::class.java)
        weatherDataVM!!.getViewState().observe(this, Observer { viewState ->
            when (viewState) {
                is ErrorState -> {
                    setViewVisibility(true,false,false)
                }
                is ProgressState -> {
                    setViewVisibility(false,true,false)
                }
                is SuccessState -> {
                    setViewVisibility(false,false,true)
                    val weatherData: WeatherData = viewState.weatherData
                    setView(weatherData)
                }
            }
        })
    }

    private fun setView(weatherData:WeatherData) {
        var temperature : Double = weatherData?.main?.temp /10
        var visibility : Double = (weatherData?.visibility/1000).toDouble()
        var humidity : Int = weatherData?.main?.humidity

        cityTV.setText(cityName!!.capitalize())
        temperatureTV.setText(String.format("%.2f", temperature))
        humidityTV.setText(humidity.toString())
        cloudsTV.setText(weatherData.clouds?.all?.toString())
        visibilityTV.setText(visibility.toString())
        windTV.setText(weatherData.wind?.speed?.toString())
        pressureTV.setText(weatherData?.main.pressure?.toString())

        var weatherIconURL: String = "http://openweathermap.org/img/wn/" + weatherData?.weather[0]?.icon + "@2x.png"
        Glide.with(this)
            .load(weatherIconURL)
            .centerCrop()
            .into(weatherIV)
    }

    fun setViewVisibility(ErrorState:Boolean, ProgressState
    :Boolean, SuccessState:Boolean){
        errorTV.visibility = if(ErrorState == true) View.VISIBLE else View.GONE
        progressBar.visibility = if(ProgressState == true) View.VISIBLE else View.GONE
        weatherDataHolder.visibility = if(SuccessState == true) View.VISIBLE else View.GONE
    }

    private fun initData() {
        weatherDataVM!!.loadWeatherData(cityName)
    }

}