package com.vince.shambafirm.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentHomeBinding
import com.vince.shambafirm.viewModels.HomeViewModel
import com.vince.shambafirm.viewModels.HomeViewModelFactory
import de.hdodenhof.circleimageview.CircleImageView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = HomeViewModelFactory(
            requireActivity().application,
            requireActivity().activityResultRegistry,
            this
        )
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        setupGreetings()
        setupDate()
        setupMenuButton()
        setupCardViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupGreetings() {
        val calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)

        val greetingText = when (hour) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            else -> "Good Night"
        }

        binding.greetingText.text = greetingText
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupDate() {
        val currentDateTime = LocalDateTime.now()
        val formattedDate = currentDateTime.format(DateTimeFormatter.ofPattern("EEEE d'${getDaySuffix(currentDateTime.dayOfMonth)}' MMMM yyyy"))
        binding.date.text = formattedDate
    }

    private fun setupMenuButton() {
        binding.menuButton.setOnClickListener {
            val menuFragment = Menu()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flFragment, menuFragment)
            transition.commit()
        }
    }

    private fun setupCardViews() {
        binding.apply {
            farmHireEquipment.setOnClickListener { replaceFragment(FarmHireEquipment()) }
            myFarm.setOnClickListener { replaceFragment(MyFarm()) }
            crops.setOnClickListener { replaceFragment(MyCrops()) }
            livestock.setOnClickListener { replaceFragment(MyLivestock()) }
            farmInputs.setOnClickListener { replaceFragment(FarmInputs()) }
            harvesting.setOnClickListener { replaceFragment(Harvesting()) }
            addWallet.setOnClickListener { replaceFragment(WalletSplashScreen()) }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
        transition.replace(R.id.flFragment, fragment)
        transition.commit()
    }

    private fun getDaySuffix(day: Int): String {
        return when (day) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }
    }

    fun observeViewModel() {
        homeViewModel.temperatureValue.observe(viewLifecycleOwner) {
            binding.temperature.text = it
        }
        homeViewModel.cityName.observe(viewLifecycleOwner) {
            binding.location.text = it
        }
    }
}
