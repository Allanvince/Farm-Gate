package com.vince.shambafirm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentDroneSprayingBinding
import com.vince.shambafirm.viewModels.DroneSprayingViewModel

class DroneSpraying : Fragment() {

    private lateinit var binding:FragmentDroneSprayingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDroneSprayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            val farmHireEquipmentFragment = FarmHireEquipment()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, farmHireEquipmentFragment)
            transition.commit()
        }
    }

}