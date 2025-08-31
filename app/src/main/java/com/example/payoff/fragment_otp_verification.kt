package com.example.payoff

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.payoff.databinding.FragmentOtpVerificationBinding

class fragment_otp_verification : Fragment() {

    private var _binding: FragmentOtpVerificationBinding? = null
    private val binding get() = _binding!!
    //private lateinit var auth: FirebaseAuth
    private lateinit var storedVerificationId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpVerificationBinding.inflate(inflater, container, false)
        //auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVerifyOtp.setOnClickListener {
            val otpCode = binding.etOtp.text.toString()

            if (verifyOtp(otpCode)) {
                openMainActivity()
            } else {
                Toast.makeText(requireContext(), "Invalid OTP", Toast.LENGTH_SHORT).show()
            }
            //verifyOtp(otpCode)
        }
    }

    private fun openMainActivity() {
        val sharedPreferences = requireActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

        // Check if a PIN is already set
        val storedPin = sharedPreferences.getString("appPin", null)

        if (storedPin == null) {
            // No PIN, navigate to PinSetupFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PinSetup())
                .addToBackStack(null)
                .commit()
        } else {
            // PIN exists, open MainActivity
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun verifyOtp(otpCode: String): Boolean {

        return otpCode == "0000"
    }

//    private fun verifyOtp(otpCode: String) {
//        val credential = PhoneAuthProvider.getCredential(storedVerificationId, otpCode)
//        auth.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                navigateToHomePage()
//            } else {
//                Toast.makeText(requireContext(), "OTP Verification Failed", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun navigateToHomePage() {
        // Replace with Home Fragment or Activity after successful OTP verification
        Toast.makeText(requireContext(), "OTP Verified Successfully!", Toast.LENGTH_SHORT).show()

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Fragment_home())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}