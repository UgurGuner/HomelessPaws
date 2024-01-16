package com.eugurguner.homelesspaws.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.eugurguner.homelesspaws.databinding.ActivitySplashBinding
import com.eugurguner.homelesspaws.presentation.login.ActivityLogin
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActivitySplash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(2000)
            Intent(this@ActivitySplash, ActivityLogin::class.java).apply {
                startActivity(this)
            }
        }
    }
}