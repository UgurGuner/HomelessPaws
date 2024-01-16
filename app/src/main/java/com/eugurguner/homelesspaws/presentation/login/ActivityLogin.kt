package com.eugurguner.homelesspaws.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eugurguner.homelesspaws.databinding.ActivityLoginBinding
import com.eugurguner.homelesspaws.presentation.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFirebaseListener()
        checkUserState()
    }

    private fun setFirebaseListener() {
        binding.btnLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
            }

            viewModel.signInUser(email, password) {
                binding.progressBar.visibility = View.GONE
                if (it) {
                    openHomePage()
                } else {
                    Toast.makeText(this, "Cannot Login Correctly, Password might be incorrect...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkUserState() {
        if (viewModel.isUserLoggedIn()) {
            openHomePage()
        }
    }

    private fun openHomePage() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}