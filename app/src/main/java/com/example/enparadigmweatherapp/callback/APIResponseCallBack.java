package com.example.enparadigmweatherapp.callback;

import com.example.enparadigmweatherapp.model.WeatherData;
import retrofit2.Call;
import retrofit2.Response;

public interface APIResponseCallBack {
    void onSuccessfulResponse(Call<WeatherData> call, Response<WeatherData> response);
    void onFailureResponse(Call<WeatherData> call, Throwable t);
}
