package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vince.shambafirm.R
import com.vince.shambafirm.viewModels.HarvestingViewModel

class Harvesting : Fragment() {

    companion object {
        fun newInstance() = Harvesting()
    }

    private lateinit var viewModel: HarvestingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_harvesting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HarvestingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}