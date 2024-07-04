package com.vince.shambafirm.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vince.shambafirm.R
import com.vince.shambafirm.activities.LoadWallet
import com.vince.shambafirm.databinding.FragmentWalletSplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WalletSplashScreen : Fragment() {

    private lateinit var binding: FragmentWalletSplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentWalletSplashScreenBinding.inflate(layoutInflater)

        val rotateAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.walletLogo.startAnimation(rotateAnimation)
                delay(1000)
                startActivity(Intent(requireContext(), LoadWallet::class.java))
                requireActivity().finish()
            }
        }

        return binding.root
    }

}