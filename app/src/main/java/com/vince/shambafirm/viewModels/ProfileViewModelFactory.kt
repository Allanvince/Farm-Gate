package com.vince.shambafirm.viewModels

import android.app.Application
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vince.shambafirm.fragments.ProfileFragment

class ProfileViewModelFactory(
    private val application: Application,
    private val activityResultRegistry: ActivityResultRegistry,
    private val profileFragment: ProfileFragment
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(application, activityResultRegistry, profileFragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}