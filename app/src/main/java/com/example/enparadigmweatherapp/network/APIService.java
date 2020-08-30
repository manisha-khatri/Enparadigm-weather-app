package com.example.enparadigmweatherapp.network;

import com.example.enparadigmweatherapp.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // http://api.openweathermap.org/data/2.5/weather?q=dehradun&appid=41392994adfbc49bb44dddf2c2589383
    @GET("data/2.5/weather")
    Call<WeatherData> customSearch(
            @Query("q") String city,
            @Query("appid") String appid
    );
}
