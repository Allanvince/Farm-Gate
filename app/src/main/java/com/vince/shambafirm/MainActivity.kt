package com.vince.shambafirm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vince.shambafirm.databinding.ActivityMainBinding
import com.vince.shambafirm.fragments.FieldView
import com.vince.shambafirm.fragments.Graphs
import com.vince.shambafirm.fragments.HomeFragment
import com.vince.shambafirm.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val homeFragment = HomeFragment()
        val graphsFragment = Graphs()
        val profileFragment = ProfileFragment()
        val fieldViewFragment = FieldView()

        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.graph -> setCurrentFragment(graphsFragment)
                R.id.fieldView -> setCurrentFragment(fieldViewFragment)
                R.id.profile -> setCurrentFragment(profileFragment)
            }
            true
        }

    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

}