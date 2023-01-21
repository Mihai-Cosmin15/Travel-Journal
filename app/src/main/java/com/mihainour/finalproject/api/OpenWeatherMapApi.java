package com.mihainour.finalproject.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenWeatherMapApi {

    @GET("")
    Call<List<Weather>> getWeather();
}
