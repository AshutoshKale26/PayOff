package com.example.payoff

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.payoff.databinding.FragmentSignInBinding


class fragment_sign_in : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    //private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        //auth = FirebaseAuth.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()

            if (phoneNumber == "5678" && password == "0000") {
                // Directly navigate to OTP Verification Fragment
                onLoginSuccess()
                navigateToOtpVerification()
            }
            else

                Toast.makeText(requireContext(), "Invalide Username or password", Toast.LENGTH_SHORT).show()
            //sendOtp(phoneNumber)
        }

        binding.btnToggleSignIn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment_sign_up())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun onLoginSuccess() {
        val sharedPreferences = requireContext().getSharedPreferences("userPrefs", MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

//        val intent = Intent(requireContext(), MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)
//        requireActivity().finish()
    }

    private fun sendOtp(phone: String) {
        // Firebase phone authentication logic here
        // Navigate to OTP Verification Fragment after sending OTP
        navigateToOtpVerification()
    }

    private fun navigateToOtpVerification() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment_otp_verification())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}