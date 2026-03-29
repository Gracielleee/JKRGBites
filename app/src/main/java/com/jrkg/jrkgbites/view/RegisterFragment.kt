package com.jrkg.jrkgbites.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.utils.ValidationUtils
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText

    private lateinit var checkBoxTermsAgreement: CheckBox

    private lateinit var registerButton: Button
    private lateinit var googleSignInButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var gotoLogin: TextView

    private var isValidUsernameFormat: Boolean = false
    private var isValidEmailFormat: Boolean = false
    private var isValidPasswordFormat: Boolean = false

    private var isTermsAndConditionsAccepted: Boolean = false

    private lateinit var viewModel: MainViewModel
    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            onGoogleSignInSuccess(account.idToken!!)
        } catch (e: ApiException) {
            Toast.makeText(requireContext(), "Google Sign-In failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

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
        googleSignInButton = view.findViewById(R.id.btnGoogleSignIn)

        val factory = MainViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        setupGoogleSignIn()
        setupListeners()
    }

    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun onGoogleSignInPressed() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun onGoogleSignInSuccess(idToken: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.signInWithGoogle(idToken).collectLatest { authResult ->
                when (authResult) {
                    is AuthResult.Success -> {
                        Toast.makeText(requireContext(), "Signed in with Google", Toast.LENGTH_SHORT).show()
                        navigateToMain()
                    }
                    is AuthResult.Error -> {
                        Toast.makeText(requireContext(), authResult.message, Toast.LENGTH_SHORT).show()
                    }
                    is AuthResult.Loading -> {
                        Toast.makeText(requireContext(), "Signing in with Google...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun navigateToMain() {
        val action = R.id.action_loginFragment_to_nav_home
        val options = androidx.navigation.navOptions {
            popUpTo(R.id.registerFragment) { inclusive = true }
        }
        findNavController().navigate(action, null, options)
    }

    private fun setupListeners() {
        // Setup clickable spans for T&C and Privacy Policy
        val fullText = getString(R.string.checkboxLabel_TermsAgreement)
        val spannableString = SpannableString(fullText)

        val tcClickable = object : ClickableSpan() {
            override fun onClick(view: View) {
                showGuidesDialog("Terms and Conditions", "TermsOfService.txt")
            }
        }

        val ppClickable = object : ClickableSpan() {
            override fun onClick(view: View) {
                showGuidesDialog("Privacy Policy", "PrivacyPolicy.txt")
            }
        }

        val tcText = "Terms and Conditions"
        val ppText = "Privacy Policy"
        val tcStart = fullText.indexOf(tcText)
        val ppStart = fullText.indexOf(ppText)

        if (tcStart != -1) {
            spannableString.setSpan(tcClickable, tcStart, tcStart + tcText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(ForegroundColorSpan(requireContext().getColor(R.color.md_theme_primary)), tcStart, tcStart + tcText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (ppStart != -1) {
            spannableString.setSpan(ppClickable, ppStart, ppStart + ppText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(ForegroundColorSpan(requireContext().getColor(R.color.md_theme_primary)), ppStart, ppStart + ppText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        checkBoxTermsAgreement.text = spannableString
        checkBoxTermsAgreement.movementMethod = LinkMovementMethod.getInstance()

        etUsername.addTextChangedListener {
            isValidUsernameFormat = ValidationUtils.validateUsernameFormat(etUsername)
        }

        etEmail.addTextChangedListener {
            isValidEmailFormat = ValidationUtils.validateEmailFormat(etEmail)
        }

        etPassword.addTextChangedListener {
            isValidPasswordFormat = ValidationUtils.validatePasswordFormat(etPassword)
        }

        checkBoxTermsAgreement.setOnCheckedChangeListener { _, isChecked ->
            isTermsAndConditionsAccepted = isChecked
        }

        registerButton.setOnClickListener {
            onRegisterButtonPressed()
        }

        googleSignInButton.setOnClickListener {
            onGoogleSignInPressed()
        }

        gotoLogin.setOnClickListener {
            onGoToLoginPressed()
        }

        backButton.setOnClickListener {
            onGoToLoginPressed()
        }
    }

    private fun showGuidesDialog(title: String, fileName: String) {
        try {
            // Load text from GuidesTexts (should be in assets, but checking directory structure)
            // User provided path: JKRGBites\AppGuides\GuidesTexts\TermsOfService.txt
            // In Android, these should ideally be in assets. Assuming they are accessible via assets for now.
            val content = requireContext().assets.open(fileName).bufferedReader().use { it.readText() }
            AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("OK", null)
                .show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Could not load $title", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onRegisterButtonPressed() {
        val usernameInput = etUsername.text.toString().trim()
        val emailInput = etEmail.text.toString().trim()
        val passwordInput = etPassword.text.toString().trim()
        val confirmPasswordInput = etConfirmPassword.text.toString().trim()
        val doPasswordsMatch = passwordInput == confirmPasswordInput

        if (usernameInput.isNotEmpty() &&
            emailInput.isNotEmpty() &&
            passwordInput.isNotEmpty() &&
            confirmPasswordInput.isNotEmpty() &&
            doPasswordsMatch &&
            isValidUsernameFormat &&
            isValidEmailFormat &&
            isValidPasswordFormat &&
            isTermsAndConditionsAccepted) {

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.signUp(emailInput, passwordInput, usernameInput).collectLatest { authResult ->
                    when (authResult) {
                        is AuthResult.Success -> {
                            Toast.makeText(requireContext(), "Registration Successful. Please login.", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.to_loginFragment)
                        }
                        is AuthResult.Error -> {
                            Toast.makeText(requireContext(), "Registration Failed: ${authResult.message}", Toast.LENGTH_SHORT).show()
                        }
                        is AuthResult.Loading -> {
                            Toast.makeText(requireContext(), "Registering...", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else if (!isTermsAndConditionsAccepted) {
            Toast.makeText(requireContext(), "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show()
        } else if (usernameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else if (!doPasswordsMatch) {
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Invalid input. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onGoToLoginPressed() {
        findNavController().navigate(R.id.to_loginFragment)
    }
}
