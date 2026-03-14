package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.utils.ValidationUtils
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ForgotPasswordDialog : DialogFragment(R.layout.dialog_forgot_password) {

    private lateinit var submitButton: Button
    private lateinit var backtoLogin: LinearLayout
    private lateinit var etEmail: TextInputEditText

    private var isEmailFormatValid: Boolean = false
    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton = view.findViewById(R.id.btnSubmit)
        backtoLogin = view.findViewById(R.id.touchBackToLogin)
        etEmail = view.findViewById(R.id.etEmail)

        val factory = MainViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

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

        if (!isEmailFormatValid) {
            Toast.makeText(
                requireContext(),
                getString(R.string.instructions_ForgotPasswordDialog),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sendPasswordResetEmail(emailInput).collectLatest { success ->
                if (success) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.display_ForgotPasswordDialog),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.header_ForgotPasswordDialog),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun onBackToLoginPressed() {
        findNavController().navigate(R.id.to_loginFragment)
    }



}