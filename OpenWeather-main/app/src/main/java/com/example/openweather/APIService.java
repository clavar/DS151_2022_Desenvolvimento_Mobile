package com.example.openweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("/data/2.5/weather")
    Call<Dados> getDados(@Query(value = "q", encoded = true) String cidade,
                         @Query("appid") String id);

}
