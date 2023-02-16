package com.example.consultatiempo.retrofitutils;

import com.example.consultatiempo.retrofitdata.WeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRestService {
    public static final String BASE_URL = "https://api.darksky.net/forecast/";


    @GET("11ce4328111023379e0fdc9d28c24a02/{lat},{lon}/")
    Call<WeatherResult> getWeather(@Path("lat") double lat, @Path("lon") double lon, @Query("exclude") String exclude,
                                   @Query("lang") String lang);
}
