package com.example.enparadigmweatherapp.view.state;

import com.example.enparadigmweatherapp.model.WeatherData;

public class SuccessState implements ViewState{
   public  WeatherData weatherData;

    public SuccessState(WeatherData weatherData){
        this.weatherData = weatherData;
    }
}
