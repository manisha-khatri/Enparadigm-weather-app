package com.example.enparadigmweatherapp.repository;

import android.util.Log;
import com.example.enparadigmweatherapp.callback.APIResponseCallBack;
import com.example.enparadigmweatherapp.model.WeatherData;
import com.example.enparadigmweatherapp.network.APIService;
import com.example.enparadigmweatherapp.network.Retrofit2Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepositoryImpl {
    APIService apiService;
    Call<WeatherData> call;

    public WeatherRepositoryImpl(){
        apiService = Retrofit2Client.getRetrofitInstance().create(APIService.class);
    }

    public void fetchWeatherData(APIResponseCallBack apiResponseCallBack, String city){
        call = apiService.customSearch(
                city,
                "41392994adfbc49bb44dddf2c2589383"
        );
        fetchNewsFromServer(call, apiResponseCallBack);
    }

    private void fetchNewsFromServer(Call<WeatherData> call, final APIResponseCallBack apiResponseCallBack ) {
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                Log.e("Response", response.toString());
                apiResponseCallBack.onSuccessfulResponse(call, response);
            }
            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.e("Error", t.getMessage());
                apiResponseCallBack.onFailureResponse(call,t);
            }
        });
    }
}
