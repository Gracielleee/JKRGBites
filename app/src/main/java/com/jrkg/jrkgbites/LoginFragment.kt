package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.utils.ValidationUtils

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import androidx.navigation.navOptions
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var checkBoxKeepMeLoggedIn: CheckBox
    private lateinit var loginButton: Button
    private lateinit var bioAuthButton: Button
    private lateinit var goToForgotPassword: TextView
    private lateinit var gotoRegister: TextView

    private var isValidEmailFormat: Boolean = false
    private var isAutoLoginEnabled: Boolean = false

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        checkBoxKeepMeLoggedIn = view.findViewById(R.id.checkboxKeepMeLoggedIn)
        loginButton = view.findViewById(R.id.btnLogin)
        bioAuthButton = view.findViewById(R.id.btnBioAuth)
        goToForgotPassword = view.findViewById(R.id.txtForgotPassword)
        gotoRegister = view.findViewById(R.id.txtGotoRegister)

        // ViewModel
        val factory = MainViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        setupListeners()
    }

    private fun setupListeners() {

        etEmail.addTextChangedListener {
            if (!ValidationUtils.validateEmailFormat(etEmail)) {
                isValidEmailFormat = false;
            }
            else {
                isValidEmailFormat = true;
            }
        }

        etPassword.addTextChangedListener {
        }

        checkBoxKeepMeLoggedIn.setOnCheckedChangeListener{ _, isChecked ->
            updateAutoLoginStatus(isChecked)
        }

        loginButton.setOnClickListener {
            onLoginButtonPressed()
        }

        bioAuthButton.setOnClickListener {
            onBioAuthButtonPressed()
        }

        goToForgotPassword.setOnClickListener {
            onGoToForgotPasswordPressed()
        }

        gotoRegister.setOnClickListener {
            onGoToRegisterPressed()
        }

    }

    private fun onLoginButtonPressed() {
        val emailInput = etEmail.text.toString().trim()
        val passwordInput = etPassword.text.toString().trim()

        if (emailInput.isEmpty() || passwordInput.isEmpty()) {
            Toast.makeText(requireContext(), "Test: Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidEmailFormat) {
            Toast.makeText(requireContext(), "Test: Invalid email format", Toast.LENGTH_SHORT).show()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.login(emailInput, passwordInput).collectLatest { authResult ->
                when (authResult) {
                    is AuthResult.Success -> {
                        val message = if (isAutoLoginEnabled) {
                            "Test: Login Successful. Auto Login Enabled."
                        } else {
                            "Test: Login Successful. Auto Login Disabled."
                        }
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
//                        findNavController().navigate(R.id.action_loginFragment_to_nav_home)
                        val action = R.id.action_loginFragment_to_nav_home
                        val navOptions = navOptions {
                            popUpTo(R.id.loginFragment) { inclusive = true } // Remove from backstack
                        }
                        findNavController().navigate(action, null, navOptions)
                    }
                    is AuthResult.Error -> {
                        Toast.makeText(requireContext(), "Test: Login Failed. ${authResult.message}", Toast.LENGTH_SHORT).show()
                    }
                    is AuthResult.Loading -> {
                        // Optionally show a loading indicator
                        Toast.makeText(requireContext(), "Test: Logging in...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun onBioAuthButtonPressed(){
        Toast.makeText(requireContext(), "Test: Biometric Authentication not yet implemented.", Toast.LENGTH_SHORT).show()
    }

    private fun updateAutoLoginStatus(isChecked: Boolean) {
        isAutoLoginEnabled = isChecked
    }

    private fun onGoToRegisterPressed() {
        findNavController().navigate(R.id.to_registerFragment)
    }

    private fun onGoToForgotPasswordPressed() {
        findNavController().navigate(R.id.to_forgotPasswordDialog)
    }

}
