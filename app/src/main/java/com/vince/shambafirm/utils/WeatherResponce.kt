package com.vince.shambafirm.utils

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val main: Main?,
    val name: String?,
    val wind: Wind?,
    val rain: Rain?
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Wind(
    val speed: Double
)

data class Rain(
    @SerializedName("1h") val rainValue: Double?
)

/** {
"coord": {
"lon": -0.13,
"lat": 51.51
},
"weather": [
{
"id": 500,
"main": "Rain",
"description": "light rain",
"icon": "10d"
}
],
"main": {
"temp": 283.58,
"feels_like": 280.53,
"temp_min": 282.59,
"temp_max": 284.82,
"pressure": 1016,
"humidity": 81
},
"wind": {
"speed": 3.6,
"deg": 150
},
"rain": {
"1h": 0.5
},
"clouds": {
"all": 0
},
"dt": 1485791400,
"sys": {
"type": 1,
"id": 5091,
"message": 0.0103,
"country": "GB",
"sunrise": 1485762037,
"sunset": 1485794875
},
"timezone": 0,
"id": 2643743,
"name": "London",
"cod": 200
}
**/