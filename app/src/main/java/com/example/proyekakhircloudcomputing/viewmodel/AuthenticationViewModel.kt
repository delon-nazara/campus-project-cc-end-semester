package com.example.proyekakhircloudcomputing.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationViewModel : ViewModel() {

    private var authentication: FirebaseAuth = Firebase.auth

    private var _userAuthState = MutableStateFlow(authentication.currentUser)
    val userAuthState: StateFlow<FirebaseUser?> = _userAuthState.asStateFlow()

    private var _errorNameState = MutableStateFlow<String?>(null)
    val errorNameState: StateFlow<String?> = _errorNameState.asStateFlow()

    private var _errorEmailState = MutableStateFlow<String?>(null)
    val errorEmailState: StateFlow<String?> = _errorEmailState.asStateFlow()

    private var _errorPasswordState = MutableStateFlow<String?>(null)
    val errorPasswordState: StateFlow<String?> = _errorPasswordState.asStateFlow()

    fun register(name: String, email: String, password: String): Boolean {
        var registerSuccess = false

        if (isNameInputValid(name) && isEmailInputValid(email) && isPasswordInputValid(password)) {
            authentication.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _userAuthState.value = authentication.currentUser
                    registerSuccess = true
                    clearErrorState()
                }
                .addOnFailureListener { exception ->
                    _userAuthState.value = null
                    registerSuccess = false
                    when (exception) {
                        is FirebaseAuthWeakPasswordException -> {
                            _errorPasswordState.value = "Password you entered is too weak"
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            _errorEmailState.value = "Invalid email or password"
                        }
                        is FirebaseAuthUserCollisionException -> {
                            _errorEmailState.value = "Email has been used"
                        }
                        else -> {
                            _errorPasswordState.value = "Register error, try again"
                        }
                    }
                }
        }

        return registerSuccess
    }

    fun login(email: String, password: String): Boolean {
        var loginSuccessful = false

        if (isEmailInputValid(email) && isPasswordInputValid(password)) {
            authentication.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _userAuthState.value = authentication.currentUser
                    loginSuccessful = true
                    clearErrorState()
                }
                .addOnFailureListener { exception ->
                    _userAuthState.value = null
                    loginSuccessful = false
                    when (exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            _errorPasswordState.value = "Wrong email or password"
                        }
                        else -> {
                            _errorPasswordState.value = "Login error, try again"
                        }
                    }
                }
        }

        return loginSuccessful
    }

    private fun isNameInputValid(name: String): Boolean {
        if (name.isEmpty()) {
            _errorNameState.value = "Name cannot be empty"
            return false
        } else if (!name.matches(Regex("^[a-zA-Z ]+$"))) {
            _errorNameState.value = "Name can only consist of alphabet"
            return false
        } else if (name.length < 3 || name.length > 30) {
            _errorNameState.value = "Name must consist of 3-30 characters"
            return false
        } else {
            _errorNameState.value = null
            return true
        }
    }

    private fun isEmailInputValid(email: String): Boolean {
        if (email.isEmpty()) {
            _errorEmailState.value = "Email cannot be empty"
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorEmailState.value = "Invalid email address format"
            return false
        } else {
            _errorEmailState.value = null
            return true
        }
    }

    private fun isPasswordInputValid(password: String): Boolean {
        if (password.isEmpty()) {
            _errorPasswordState.value = "Password cannot be empty"
            return false
        } else if (password.length < 6) {
            _errorPasswordState.value = "Password must consist of at least 6 characters"
            return false
        } else {
            _errorPasswordState.value = null
            return true
        }
    }

    private fun clearErrorState() {
        _errorNameState.value = null
        _errorEmailState.value = null
        _errorPasswordState.value = null
    }

}