package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.vince.shambafirm.R
import com.vince.shambafirm.adapter.FarmRecordAdapter
import com.vince.shambafirm.databinding.FragmentMyFarmBinding
import com.vince.shambafirm.viewModels.AddFarmViewModel
import com.vince.shambafirm.viewModels.MyFarmViewModel

class MyFarm : Fragment() {
    private lateinit var binding:FragmentMyFarmBinding

    private lateinit var myFarmViewModel: MyFarmViewModel
    private lateinit var addFarmViewModel: AddFarmViewModel
    private lateinit var farmRecordAdapter: FarmRecordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentMyFarmBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFarmViewModel = ViewModelProvider(requireActivity())[AddFarmViewModel::class.java]

        farmRecordAdapter = FarmRecordAdapter(listOf())

        // Observe the LiveData in the AddFarmViewModel
        observeViewModel()

        setUpRecyclerView()

        // Set up the click listeners
        binding.myFarmBackArrow.setOnClickListener {
            val homeFragment = HomeFragment()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, homeFragment)
            transition.commit()
        }

        binding.addFarmCardView.setOnClickListener {
            val addFarm = AddFarm()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, addFarm)
            transition.commit()
        }
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.myFarmRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = farmRecordAdapter
    }

    private fun observeViewModel() {
        addFarmViewModel.farmRecords.observe(viewLifecycleOwner){
            it?.let {
                farmRecordAdapter.updateRecords(it)
            }
        }
    }


}