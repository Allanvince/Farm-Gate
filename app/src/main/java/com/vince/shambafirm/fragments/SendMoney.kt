package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.FragmentSendMoneyBinding
import com.vince.shambafirm.viewModels.SendMoneyViewModel

class SendMoney : Fragment() {

    private lateinit var binding: FragmentSendMoneyBinding
    private lateinit var viewModel: SendMoneyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendMoneyBinding.inflate(layoutInflater)

        val sendMoneyBackArrow = binding.sendMoneyBackArrow
        sendMoneyBackArrow.setOnClickListener {
            val transactFragment = Transact()
            val transition: FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.flLoadWalletFragment, transactFragment)
            transition.commit()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SendMoneyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}