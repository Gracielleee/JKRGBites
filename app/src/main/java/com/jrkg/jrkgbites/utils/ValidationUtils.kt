package com.jrkg.jrkgbites.utils

import android.util.Patterns
import android.widget.EditText

object ValidationUtils {

    private val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{6,}$")

    private val usernamePattern = Regex("^[a-zA-Z][a-zA-Z0-9_-]{2,15}\$")

    fun validateUsernameFormat(editText: EditText): Boolean {
        val username = editText.text.toString().trim()
        if (username.length < 3) {
            editText.error = "Nickname is too short"
            return false
        }
        if (username.length > 15) {
            editText.error = "Nickname is too long"
            return false
        }
        if (!usernamePattern.matches(username)) {
            editText.error = "Only letters, numbers, hyphens, and underscores are allowed."
            return false
        }
        if (username.startsWith('-') || username.startsWith('_')) {
            editText.error = "Username cannot start with a hyphen or underscore.";
            return false
        }
        if (username.endsWith('-') || username.endsWith('_')) {
            editText.error =  "Username cannot end with a hyphen or underscore.";
            return false
        }
        else {
            editText.error = null
            return true
        }
    }

    fun validateEmailFormat(editText: EditText): Boolean {
        val email = editText.text.toString().trim()
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText.error = "Invalid email format"
            false
        } else {
            editText.error = null
            true
        }
    }

    fun validatePasswordFormat(editText: EditText): Boolean {
        val password = editText.text.toString().trim()
        if (password.length < 6) {
            editText.error = "Password must be at least 6 characters"
            return false
        }
        if (!passwordPattern.matches(password)) {
            editText.error = "Password must and can only contain letters, numbers, and special characters"
            return false
        } else {
            editText.error = null
            return true

        }
    }
}