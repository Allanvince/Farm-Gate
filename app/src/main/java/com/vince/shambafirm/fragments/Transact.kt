package com.vince.shambafirm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentTransactBinding


class Transact : Fragment() {

    private lateinit var binding: FragmentTransactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTransactBinding.inflate(layoutInflater)

        val sendMoneyButton = binding.sendMoneyButton
        sendMoneyButton.setOnClickListener {
            val sendMoneyFragment = SendMoney()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flLoadWalletFragment, sendMoneyFragment)
            transition.commit()
        }

        val depositMoneyButton = binding.depositMoneyButton
        depositMoneyButton.setOnClickListener {
            val depositMoneyFragment = DepositMoney()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flLoadWalletFragment, depositMoneyFragment)
            transition.commit()
        }

        return binding.root
    }

}