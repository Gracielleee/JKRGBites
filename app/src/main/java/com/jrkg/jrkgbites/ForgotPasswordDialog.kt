package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.utils.ValidationUtils

class ForgotPasswordDialog : DialogFragment(R.layout.dialog_forgot_password) {

    private lateinit var submitButton: Button
    private lateinit var backtoLogin: LinearLayout
    private lateinit var etEmail: TextInputEditText

    private var isEmailFormatValid: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton = view.findViewById(R.id.btnSubmit)
        backtoLogin = view.findViewById(R.id.touchBackToLogin)
        etEmail = view.findViewById(R.id.etEmail)

        setupListeners()
    }

    private fun setupListeners() {

        etEmail.addTextChangedListener {
            if (!ValidationUtils.validateEmailFormat(etEmail)) {
                etEmail.error = "Invalid email format"
                isEmailFormatValid = false;
            }
            else {
                isEmailFormatValid = true;
            }
        }

        submitButton.setOnClickListener {
            onSubmitButtonPressed()
        }

        backtoLogin.setOnClickListener {
            onBackToLoginPressed()
        }
    }

    private fun onSubmitButtonPressed() {
        val emailInput = etEmail.text.toString().trim()

        //Option 1: Reset Success. Email format is valid and Email is found in database
        if (isEmailFormatValid) {
            Toast.makeText(
                requireContext(),
                "Test: We sent you a link to reset your password",
                Toast.LENGTH_SHORT
            ).show()

        //Option 2: Reset Failure. Email format is valid but Email is NOT found in database
//        }else if (isEmailFormatValid) {
//            Toast.makeText(requireContext(), "Test: Email does not match a user in our system.", Toast.LENGTH_SHORT).show()
//
        //Option 2: Reset Failure. Email format is invalid.
        } else {
            Toast.makeText(requireContext(), "Test: Invalid email format. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun onBackToLoginPressed() {
        findNavController().navigate(R.id.to_loginFragment)
    }



}