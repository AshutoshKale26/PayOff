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
import com.example.payoff.databinding.FragmentSignupBinding

class fragment_sign_up : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    //private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        //auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val dob = binding.etDOB.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (fullName.isNotBlank() && dob.isNotBlank() && phoneNumber.isNotBlank() && password.isNotBlank()) {
                saveUserData(fullName, dob, phoneNumber, email, password)
                onLoginSuccess()
                //Toast.makeText(requireContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show()
                navigateToOtpVerification()
            } else {
                Toast.makeText(requireContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }

            //sendOtp(phoneNumber)
        }

        binding.btnToggleSignUp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment_sign_in())
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

    private fun saveUserData(
        fullName: String,
        dob: String,
        phoneNumber: String,
        email: String,
        password: String
    ) {
        with(sharedPreferences.edit()) {
            putString("fullName", fullName)
            putString("dob", dob)
            putString("phone", phoneNumber)
            putString("email", email)
            putString("password", password)
            putBoolean("isLoggedIn", true)
            apply()
        }

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