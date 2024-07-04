package com.vince.shambafirm.viewModels

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vince.shambafirm.fragments.HomeFragment
import com.vince.shambafirm.utils.OpenWeather
import com.vince.shambafirm.utils.RetrofitService
import com.vince.shambafirm.utils.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale

class HomeViewModel(
    private val application: Application,
    private val activityResultRegistry: ActivityResultRegistry,
    private val homeFragment: HomeFragment
) : ViewModel() {
    private val context: Context get() = application.applicationContext

    private val apiService = RetrofitService.create(OpenWeather::class.java)

    private val _temperatureValue = MutableLiveData<String>()
    val temperatureValue: LiveData<String> = _temperatureValue

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val apiKey = "ae15faf6c070598d0af766994094815c"

    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    private lateinit var locationPermissionLauncher: ActivityResultLauncher<String>

    init {
        initLocationPermissionLauncher()
        checkLocationPermissionAndFetchData()
    }

    private fun initLocationPermissionLauncher() {
        locationPermissionLauncher = activityResultRegistry.register(
            "location_permission",
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                fetchLocation()
            } else {
                // Handle the case where permission is not granted
                _cityName.value = "Location permission denied"
                _temperatureValue.value = "N/A"
            }
        }
    }

    private fun checkLocationPermissionAndFetchData() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            fetchLocation()
        } else {
            requestLocationPermission()
        }
    }

    private fun fetchLocation() {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.also {
                    _location.value = it
                    convertLocationToCity(it.latitude, it.longitude)
                }
            }
        } catch (e: SecurityException) {
            // Handle the security exception
            _cityName.value = "Error accessing location"
            _temperatureValue.value = "N/A"
        }
    }

    private fun convertLocationToCity(latitude: Double, longitude: Double) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val cityName = addresses[0].locality
                _cityName.value = cityName
                fetchWeatherData(cityName) // Use the city name to fetch weather data
            } else {
                // Handle the case where the city name is not available
                _cityName.value = "City not found"
                _temperatureValue.value = "N/A"
            }
        } catch (e: IOException) {
            // Handle the exception
            _cityName.value = "Error fetching city name"
            _temperatureValue.value = "N/A"
        }
    }

    private fun requestLocationPermission() {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun fetchWeatherData(cityName: String) {
        val call = apiService.getWeatherByCityName(cityName, apiKey)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let {
                        it.main?.let { mainDetails ->
                            val temperatureInCelsius = mainDetails.temp
                            _temperatureValue.value = String.format(Locale.getDefault(),
                                "%.1f°C", temperatureInCelsius)
                        }
                        homeFragment.observeViewModel()
                    }
                } else {
                    // Handle the case where the API response is not successful
                    _temperatureValue.value = "N/A"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle the failure
                Log.e("WeatherFetchError", "Failed to fetch weather data", t)
                _temperatureValue.value = "N/A"
            }
        })
    }
}
