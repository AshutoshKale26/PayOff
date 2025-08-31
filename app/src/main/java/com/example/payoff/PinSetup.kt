package com.example.payoff

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.payoff.databinding.FragmentPinSetupBinding

class PinSetup : Fragment() {

    private var _binding: FragmentPinSetupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPinSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSetPin.setOnClickListener {
            val pin = binding.etPin.text.toString()
            if (pin.length == 4) {
                savePin(pin)
                openHome()
            } else {
                Toast.makeText(context, "Please enter a 4-digit PIN", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun savePin(pin: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("appPin", pin).apply()
    }

    private fun openHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}