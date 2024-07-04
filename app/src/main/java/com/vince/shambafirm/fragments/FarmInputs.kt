package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vince.shambafirm.R
import com.vince.shambafirm.viewModels.FarmInputsViewModel

class FarmInputs : Fragment() {

    companion object {
        fun newInstance() = FarmInputs()
    }

    private lateinit var viewModel: FarmInputsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_farm_inputs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FarmInputsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}