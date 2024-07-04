package com.vince.shambafirm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.*

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)

        binding.logo.startAnimation(bounceAnimation)
        binding.appName.startAnimation(slideAnimation)

        // Use coroutines for delay
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000) // 4 seconds delay
            startActivity(Intent(this@SplashScreen, Login::class.java))
            finish()
        }
    }
}
