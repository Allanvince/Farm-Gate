package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentMenuBinding
import com.vince.shambafirm.viewModels.MenuViewModel
import com.vince.shambafirm.viewModels.MenuViewModelFactory

class Menu : Fragment() {

    private lateinit var menuViewModel: MenuViewModel
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private var backButton: Button? = null
    private var menuTemperature: TextView? = null
    private var humidityValue: TextView? = null
    private var windSpeedValueView: TextView? = null
    private var precipitationValueView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuViewModel = ViewModelProvider(this,
            MenuViewModelFactory(requireActivity().application,
                requireActivity().activityResultRegistry, this
            ))[MenuViewModel::class.java]

        menuTemperature = binding.menuTemperature
        humidityValue = binding.humidityValueText
        windSpeedValueView = binding.windSpeed
        precipitationValueView = binding.precipitationValue

        backButton = binding.backButton
        backButton?.setOnClickListener {replaceFragment(HomeFragment()) }

        val loadWalletButton = binding.loadWalletButton
        loadWalletButton.setOnClickListener {replaceFragment(WalletSplashScreen())}

    }

    private fun replaceFragment(fragment: Fragment){
        val transition:FragmentTransaction = requireFragmentManager().beginTransaction()
        transition.replace(R.id.flFragment, fragment)
        transition.commit()
    }

    fun observeViewModels() {
        menuViewModel.temperatureValue.observe(viewLifecycleOwner){
            menuTemperature?.text = it.toString()
        }
        menuViewModel.humidityValue.observe(viewLifecycleOwner){
            humidityValue?.text = it.toString()
        }
        menuViewModel.windSpeedValue.observe(viewLifecycleOwner){
            windSpeedValueView?.text = it.toString()
        }
        menuViewModel.precipitationValue.observe(viewLifecycleOwner){
            precipitationValueView?.text = it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
