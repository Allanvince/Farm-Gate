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
import com.vince.shambafirm.fragments.ProfileFragment
import java.io.IOException
import java.util.Locale

class ProfileViewModel(
    private val application: Application,
    private val activityResultRegistry: ActivityResultRegistry,
    private val profileFragment: ProfileFragment
) : ViewModel() {

    private val context: Context get() = application.applicationContext

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

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
        }
    }

    private fun convertLocationToCity(latitude: Double, longitude: Double) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val cityName = addresses[0].locality
                _cityName.value = cityName

            } else {
                // Handle the case where the city name is not available
                _cityName.value = "City not found"
            }
        } catch (e: IOException) {
            // Handle the exception
            _cityName.value = "Error fetching city name"
        }
    }

    private fun requestLocationPermission() {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


}