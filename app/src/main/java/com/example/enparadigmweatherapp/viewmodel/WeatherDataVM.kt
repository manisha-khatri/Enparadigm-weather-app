package com.example.enparadigmweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enparadigmweatherapp.callback.APIResponseCallBack
import com.example.enparadigmweatherapp.model.WeatherData
import com.example.enparadigmweatherapp.repository.WeatherRepositoryImpl
import com.example.enparadigmweatherapp.view.state.ErrorState
import com.example.enparadigmweatherapp.view.state.ProgressState
import com.example.enparadigmweatherapp.view.state.SuccessState
import com.example.enparadigmweatherapp.view.state.ViewState
import retrofit2.Call
import retrofit2.Response

class WeatherDataVM() : ViewModel() {

    private val viewState: MutableLiveData<ViewState> = MutableLiveData<ViewState>()
    private var weatherRepository : WeatherRepositoryImpl ?= null

    fun getViewState(): MutableLiveData<ViewState>{
        return viewState
    }

    fun loadWeatherData(cityName:String ?= null){
        weatherRepository = WeatherRepositoryImpl()

        viewState.setValue(ProgressState())
        weatherRepository!!.fetchWeatherData(object : APIResponseCallBack {
            override fun onSuccessfulResponse(
                call: Call<WeatherData?>?,
                response: Response<WeatherData?>?
            ) {
                handleSuccessfulResponse(call, response)
            }

            override fun onFailureResponse(
                call: Call<WeatherData?>?,
                t: Throwable?
            ) {
                viewState.setValue(ErrorState("Cannot fetch response from server"))
            }
        }, cityName)
    }

    private fun handleSuccessfulResponse(
        call: Call<WeatherData?>?,
        response: Response<WeatherData?>?
    ) {
        if (response!!.isSuccessful()) {
            val weatherData: WeatherData = response.body()!!
            viewState.setValue(SuccessState(weatherData))
        }
        else {
            viewState.setValue(ErrorState("Cannot fetch response from server"))
        }
    }
}