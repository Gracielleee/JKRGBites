package com.jrkg.jrkgbites

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.utils.ValidationUtils


class RegisterFragment : Fragment(R.layout.fragment_register) {


    private lateinit var etUsername: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText

    private lateinit var checkBoxTermsAgreement: CheckBox

    private lateinit var registerButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var gotoLogin: TextView

    private var isValidUsernameFormat: Boolean = false
    private var isValidEmailFormat: Boolean = false
    private var isValidPasswordFormat: Boolean = false

    private var isTermsAndConditionsAccepted: Boolean = false




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton = view.findViewById(R.id.btnRegister)
        gotoLogin = view.findViewById(R.id.txtBackToLogin)
        etUsername = view.findViewById(R.id.etUsername)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword)
        backButton = view.findViewById(R.id.btnBack)
        checkBoxTermsAgreement = view.findViewById(R.id.checkboxAgreement)

        setupListeners()
    }

    private fun setupListeners() {

        etUsername.addTextChangedListener {
            if (!ValidationUtils.validateUsernameFormat(etUsername)) {
                isValidUsernameFormat = false;
            }
            else {
                isValidUsernameFormat = true;
            }
        }

        etEmail.addTextChangedListener {
            if (!ValidationUtils.validateEmailFormat(etEmail)) {
                isValidEmailFormat = false;
            }
            else {
                isValidEmailFormat = true;
            }
        }

        etPassword.addTextChangedListener {
            if (!ValidationUtils.validatePasswordFormat(etPassword)) {
                isValidPasswordFormat = false;
            }
            else {
                isValidPasswordFormat = true;
            }
        }

        checkBoxTermsAgreement.setOnCheckedChangeListener { _, isChecked ->
            isTermsAndConditionsAccepted = isChecked
        }

        registerButton.setOnClickListener {
            onRegisterButtonPressed()
        }

        gotoLogin.setOnClickListener {
            onGoToLoginPressed()
        }

        backButton.setOnClickListener {
            onGoToLoginPressed()
        }

    }

    private fun onRegisterButtonPressed() {
        val usernameInput = etUsername.text.toString().trim()
        val emailInput = etEmail.text.toString().trim()
        val passwordInput = etPassword.text.toString().trim()
        val confirmPasswordInput = etConfirmPassword.text.toString().trim()
        val doPasswordsMatch = passwordInput == confirmPasswordInput

        // Log debugging for the inputs
        Log.d("RegistrationDebug", "Username: $usernameInput")
        Log.d("RegistrationDebug", "Email: $emailInput")
        Log.d("RegistrationDebug", "Password: $passwordInput")
        Log.d("RegistrationDebug", "Confirm Password: $confirmPasswordInput")
        Log.d("RegistrationDebug", "Do passwords match: $doPasswordsMatch")
        Log.d("RegistrationDebug", "Username format valid: $isValidUsernameFormat")
        Log.d("RegistrationDebug", "Email format valid: $isValidEmailFormat")
        Log.d("RegistrationDebug", "Password format valid: $isValidPasswordFormat")

        //Option 1: Register Success. All fields are valid, passwords match, agreed to terms
        if (usernameInput.isNotEmpty() &&
            emailInput.isNotEmpty() &&
            passwordInput.isNotEmpty() &&
            confirmPasswordInput.isNotEmpty() &&
            doPasswordsMatch &&
            isValidUsernameFormat &&
            isValidEmailFormat &&
            isValidPasswordFormat &&
            isTermsAndConditionsAccepted) {
            Toast.makeText(requireContext(), "Test: Successfully registered", Toast.LENGTH_SHORT)
                .show()

        } //Option 2: Register Failure. Terms and conditions not accepted
        else if (usernameInput.isNotEmpty() &&
            emailInput.isNotEmpty() &&
            passwordInput.isNotEmpty() &&
            confirmPasswordInput.isNotEmpty() &&
            doPasswordsMatch &&
            isValidUsernameFormat &&
            isValidEmailFormat &&
            isValidPasswordFormat &&
            !isTermsAndConditionsAccepted) {
            Toast.makeText(requireContext(), "Test: Register Failure. Please accept the Terms and Conditions", Toast.LENGTH_SHORT)
                .show()

        //Option 3: Register Failure. A field or more is empty
        } else if (usernameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty()) {
            Toast.makeText(requireContext(), "Test: Please fill in all fields", Toast.LENGTH_SHORT).show()

        //Option 4: Register Failure. Password do not match
        } else if (passwordInput != confirmPasswordInput) {
            Toast.makeText(requireContext(), "Test: Passwords do not match", Toast.LENGTH_SHORT).show()

        //Option 5: Register Failure. Account/Email already exists in the database
//        } else if (usernameInput.isNotEmpty() && emailInput.isNotEmpty() && passwordInput.isNotEmpty() && confirmPasswordInput.isNotEmpty() &&
//            doPasswordsMatch &&
//            isValidUsernameFormat &&
//            isValidEmailFormat &&
//            isValidPasswordFormat) {
//            Toast.makeText(requireContext(), "Test: Your account already exists. Please login instead.", Toast.LENGTH_SHORT)
//                .show()

        //Option 6: Register Failure. Invalid input
        } else {
            Toast.makeText(requireContext(), "Test: Invalid input. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun onGoToLoginPressed() {
        findNavController().navigate(R.id.to_loginFragment)
    }






}