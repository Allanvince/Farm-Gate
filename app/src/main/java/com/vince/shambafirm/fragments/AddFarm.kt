package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentAddFarmBinding
import com.vince.shambafirm.viewModels.AddFarmViewModel

class AddFarm : Fragment() {
    private lateinit var binding: FragmentAddFarmBinding
    private lateinit var addFarmViewModel: AddFarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddFarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFarmViewModel = ViewModelProvider(requireActivity())[AddFarmViewModel::class.java]

        val backArrow = binding.backArrow
        backArrow.setOnClickListener {
            val myFarm = MyFarm()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, myFarm)
            transition.commit()
        }

        val addFarmButton = binding.addFarmButton
        addFarmButton.setOnClickListener {
            if (validateForm()) {
                switchFragment()
            }
        }
        binding.backArrow.setOnClickListener {
            val addFarm = AddFarm()
            val transition:FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, addFarm)
            transition.commit()
        }
    }

    private fun switchFragment() {
        val myFarm = MyFarm()
        val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
        transition.replace(R.id.flFragment, myFarm)
        transition.addToBackStack(null)
        transition.commit()
    }

    private fun validateForm(): Boolean {
        val fieldKeyPairs = listOf(
            binding.farmSizeEditText to "Farm Size",
            binding.numberOfLabourers to "No. Of Labourers",
            binding.typesOfCropsGrown to "Types Of Crops"
        )

        val requiredFarmsRecords = listOf(
            binding.farmSizeEditText to "Please fill the Farm Size",
            binding.numberOfLabourers to "Please fill No of Labourers on this Farm",
            binding.typesOfCropsGrown to "Please fill Types of Crops on this farm",
        )

        var allRecordsValid = true

        for ((records, errorMessage) in requiredFarmsRecords) {
            if (records.text.trim().toString().isEmpty()) {
                records.error = errorMessage
                allRecordsValid = false
            }
        }

        if (allRecordsValid) {
            val recordsMap = hashMapOf<String, String>()
            for ((field, key) in fieldKeyPairs) {
                recordsMap[key] = field.text.trim().toString()
            }
            addFarmViewModel.setFarmRecords(recordsMap)
        }

        return allRecordsValid
    }
}
