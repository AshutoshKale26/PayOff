package com.example.payoff

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.payoff.databinding.ActivityPinPromptBinding
import com.example.payoff.databinding.FragmentPinSetupBinding

class Pin_Prompt : AppCompatActivity() {

    private lateinit var binding: ActivityPinPromptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinPromptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitPin.setOnClickListener {
            val enteredPin = binding.etPinPrompt.text.toString()
            if (validatePin(enteredPin)) {
                openMainActivity()
            } else {
                Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatePin(enteredPin: String): Boolean {
        val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val storedPin = sharedPreferences.getString("appPin", null)
        return enteredPin == storedPin
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}