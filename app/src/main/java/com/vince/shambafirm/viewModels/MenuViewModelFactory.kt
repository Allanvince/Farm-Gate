package com.vince.shambafirm.viewModels

import android.app.Application
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vince.shambafirm.fragments.Menu

class MenuViewModelFactory(
    private val application: Application,
    private val activityResultRegistry: ActivityResultRegistry,
    private val menuFragment: Menu
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MenuViewModel(application, activityResultRegistry, menuFragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}