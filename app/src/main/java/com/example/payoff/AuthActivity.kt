package com.example.payoff

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.payoff.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // User is logged in, navigate to MainActivity
            //startActivity(Intent(this, MainActivity::class.java))
            //finish() // Close AuthActivity
            navigateToMainOrPin()
        } else {
            // User is not logged in, show SignInFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment_sign_up())
                .commit()
        }

        if (savedInstanceState == null) {
            loadFragment(fragment_sign_up())
        }
    }

    private fun navigateToMainOrPin() {
        val sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        val pin = sharedPreferences.getString("appPin", null)
        if (pin.isNullOrEmpty()) {
            loadFragment(PinSetup())
        } else {
            startActivity(Intent(this, Pin_Prompt::class.java))
            finish()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}