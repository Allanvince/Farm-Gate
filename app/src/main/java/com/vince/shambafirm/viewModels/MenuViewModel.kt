package com.vince.shambafirm.viewModels

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vince.shambafirm.fragments.Menu
import com.vince.shambafirm.utils.OpenWeather
import com.vince.shambafirm.utils.RetrofitService
import com.vince.shambafirm.utils.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale

class MenuViewModel(
    private val application: Application,
    private val activityResultRegistry: ActivityResultRegistry,
    private val menuFragment: Menu
) : ViewModel() {

    private val _temperatureValue = MutableLiveData<String>()
    private val _humidityValue = MutableLiveData<String>()
    private val _windSpeedValue = MutableLiveData<String>()
    private val _precipitationValue = MutableLiveData<String>()

    val temperatureValue: LiveData<String> get() = _temperatureValue
    val humidityValue: LiveData<String> get() = _humidityValue
    val windSpeedValue: LiveData<String> get() = _windSpeedValue
    val precipitationValue: LiveData<String> get() = _precipitationValue

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> get() = _cityName

    private val context: Context get() = application.applicationContext
    private val apiService = RetrofitService.create(OpenWeather::class.java)

    private val apiKey = "ae15faf6c070598d0af766994094815c"

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> get() = _location

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
                handleLocationPermissionDenied()
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
            handleLocationAccessError()
        }
    }

    private fun convertLocationToCity(latitude: Double, longitude: Double) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val cityName = addresses[0].locality
                _cityName.value = cityName
                fetchWeatherData(cityName)
            } else {
                handleCityNotFound()
            }
        } catch (e: IOException) {
            handleCityFetchError()
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
                    if (responseData != null) {
                        val mainDetails = responseData.main
                        val windDetails = responseData.wind
                        val rainDetails = responseData.rain

                        mainDetails?.let {
                            _temperatureValue.value = String.format(Locale.getDefault(),
                                "%.1f°C", it.temp)
                            _humidityValue.value = String.format(Locale.getDefault(),
                                "%d%%", it.humidity)
                        } ?: run {
                            _temperatureValue.value = "N/A"
                            _humidityValue.value = "N/A"
                        }

                        windDetails?.let {
                            _windSpeedValue.value = String.format(Locale.getDefault(),
                                "%.1f m/s", it.speed)
                        } ?: run {
                            _windSpeedValue.value = "N/A"
                        }

                        rainDetails?.let {
                            _precipitationValue.value = String.format(Locale.getDefault(),
                                "%.1f mm", it.rainValue ?: 0.0)
                        } ?: run {
                            _precipitationValue.value = "0.0 mm"
                        }

                        menuFragment.observeViewModels()
                    }
                } else {
                    handleWeatherFetchError()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                handleWeatherFetchError()
            }
        })
    }

    private fun handleLocationPermissionDenied() {
        _cityName.value = "Location permission denied"
        _temperatureValue.value = "N/A"
        _humidityValue.value = "N/A"
        _windSpeedValue.value = "N/A"
        _precipitationValue.value = "N/A"
    }

    private fun handleLocationAccessError() {
        _cityName.value = "Error accessing location"
        _temperatureValue.value = "N/A"
        _humidityValue.value = "N/A"
        _windSpeedValue.value = "N/A"
        _precipitationValue.value = "N/A"
    }

    private fun handleCityNotFound() {
        _cityName.value = "City not found"
        _temperatureValue.value = "N/A"
        _humidityValue.value = "N/A"
        _windSpeedValue.value = "N/A"
        _precipitationValue.value = "N/A"
    }

    private fun handleCityFetchError() {
        _cityName.value = "Error fetching city name"
        _temperatureValue.value = "N/A"
        _humidityValue.value = "N/A"
        _windSpeedValue.value = "N/A"
        _precipitationValue.value = "N/A"
    }

    private fun handleWeatherFetchError() {
        _temperatureValue.value = "N/A"
        _humidityValue.value = "N/A"
        _windSpeedValue.value = "N/A"
        _precipitationValue.value = "N/A"
    }
}
