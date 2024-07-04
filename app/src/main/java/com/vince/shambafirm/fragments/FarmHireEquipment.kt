package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentFarmHireEquipmentBinding
import com.vince.shambafirm.viewModels.FarmHireEquipmentViewModel

class FarmHireEquipment : Fragment() {

    private lateinit var binding: FragmentFarmHireEquipmentBinding
    private lateinit var viewModel: FarmHireEquipmentViewModel

    private var backButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentFarmHireEquipmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton = binding.backButtonFarmEquipment
        backButton?.setOnClickListener {
            val homeFragment = HomeFragment()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, homeFragment)
            transition.commit()
        }
        val droneSpraying = binding.droneSpraying
        droneSpraying.setOnClickListener {
            val droneSprayingFragment = DroneSpraying()
            val transition:FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, droneSprayingFragment)
            transition.commit()
        }
    }

}