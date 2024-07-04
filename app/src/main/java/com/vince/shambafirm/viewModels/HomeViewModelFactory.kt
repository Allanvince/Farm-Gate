package com.vince.shambafirm.viewModels

import android.app.Application
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vince.shambafirm.fragments.HomeFragment

class HomeViewModelFactory(
    private val application: Application,
    private val activityResultRegistry: ActivityResultRegistry,
    private val homeFragment: HomeFragment
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(application, activityResultRegistry, homeFragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
