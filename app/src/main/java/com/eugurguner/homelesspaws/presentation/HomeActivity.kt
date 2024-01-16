package com.eugurguner.homelesspaws.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eugurguner.homelesspaws.R
import com.eugurguner.homelesspaws.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var selectedTab = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(onBackPressedCallBack)
        setBottomNavigationBar()
    }

    @SuppressLint("RestrictedApi")
    private fun setBottomNavigationBar() {
        val navController = findNavController(R.id.nav_host_fragment)

        navController.enableOnBackPressed(false)

        binding.bottomNavigationView.setupWithNavController(navController)

        this.selectedTab = binding.bottomNavigationView.selectedItemId

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            if (selectedTab == item.itemId) return@setOnItemSelectedListener false

            this.selectedTab = item.itemId

            when (item.itemId) {
                R.id.fragmentHome -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.fragmentDonation -> {
                    navController.navigate(R.id.donationFragment)
                    true
                }

                R.id.fragmentProfiles -> {
                    navController.navigate(R.id.profilesFragment)
                    true
                }

                R.id.fragmentLearn -> {
                    navController.navigate(R.id.learnFragment)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private var onBackPressedCallBack =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        }
}