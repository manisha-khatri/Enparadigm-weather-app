package com.example.enparadigmweatherapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2Client {
    private static Retrofit retrofit;
    private Retrofit2Client(){}

    //http://api.openweathermap.org/data/2.5/weather?q=dehradun&appid=41392994adfbc49bb44dddf2c2589383
    public static Retrofit getRetrofitInstance(){
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
