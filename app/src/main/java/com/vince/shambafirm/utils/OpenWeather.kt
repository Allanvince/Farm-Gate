package com.vince.shambafirm.utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeather {
    @GET("data/2.5/weather")
    fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>
}
