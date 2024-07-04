package com.vince.shambafirm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private var loginLinkText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginLinkText = binding.loginLink
        loginLinkText?.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}