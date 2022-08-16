package com.example.openweather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;
    private String url = "http://api.openweathermap.org/";

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

public APIService getAPIService() {return this.retrofit.create(APIService.class);}
}
