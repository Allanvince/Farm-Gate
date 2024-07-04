package com.vince.shambafirm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentWalletHomeBinding
import java.util.Calendar

class WalletHome : Fragment() {

    private lateinit var binding: FragmentWalletHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletHomeBinding.inflate(layoutInflater)

        val transactButton = binding.transactButton
        transactButton.setOnClickListener {
            val transactionFragment = Transact()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flLoadWalletFragment, transactionFragment)
            transition.commit()
        }

        val calender = Calendar.getInstance()
        val hour: Int = calender.get(Calendar.HOUR_OF_DAY)
        val greetings = binding.greetingTextView

        val morning = "Good Morning"
        val afterNoon = "Good Afternoon"
        val evening = "Good Evening"
        val night = "Good Night"
        val hi = "hi"


        if (hour in 0..11){
            greetings.text = morning
        }
        else if (hour in 12..15){
            greetings.text = afterNoon
        }
        else if (hour in 16..20){
            greetings.text = evening
        }
        else if (hour >=21 ){
            greetings.text = night
        }
        else{
            greetings.text = hi
        }

        return binding.root
    }
}