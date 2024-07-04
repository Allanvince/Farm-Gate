package com.vince.shambafirm.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.vince.shambafirm.MainActivity
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.ActivityLoadWalletBinding
import com.vince.shambafirm.fragments.HomeFragment
import com.vince.shambafirm.fragments.WalletHome
import com.vince.shambafirm.utils.Main

class LoadWallet : AppCompatActivity() {

    private lateinit var binding: ActivityLoadWalletBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadWalletBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val walletHomeFragment = WalletHome()

        setCurrentFragment(walletHomeFragment)

        binding.walletBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.walletHome -> setCurrentFragment(walletHomeFragment)
                R.id.appHome -> startActivity(Intent(this, MainActivity::class.java))
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flLoadWalletFragment, fragment)
            commit()
        }
    }
}