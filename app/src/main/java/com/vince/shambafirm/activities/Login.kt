package com.vince.shambafirm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.vince.shambafirm.MainActivity
import com.vince.shambafirm.R
import com.vince.shambafirm.databinding.ActivityLoginBinding
import com.vince.shambafirm.databinding.ActivitySignUpBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var signUpText: TextView? = null
    private var loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpText = binding.signUpLink
        signUpText?.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
        loginButton = binding.loginButton
        loginButton?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}