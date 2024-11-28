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

class AuthViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private var _userState = MutableStateFlow(auth.currentUser)
    val userState: StateFlow<FirebaseUser?> = _userState.asStateFlow()

    private var _errorNameState = MutableStateFlow<String?>(null)
    val errorNameState: StateFlow<String?> = _errorNameState.asStateFlow()

    private var _errorEmailState = MutableStateFlow<String?>(null)
    val errorEmailState: StateFlow<String?> = _errorEmailState.asStateFlow()

    private var _errorPasswordState = MutableStateFlow<String?>(null)
    val errorPasswordState: StateFlow<String?> = _errorPasswordState.asStateFlow()

    fun register(
        name: String,
        email: String,
        password: String
    ): Boolean {
        var registerSuccessful = false

        if (validateInput(name, email, password)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _userState.value = auth.currentUser
                        registerSuccessful = true
                        clearErrorState()
                    } else {
                        when (task.exception) {
                            is FirebaseAuthWeakPasswordException -> {
                                _errorPasswordState.value = "Password must consist of at least 6 characters"
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                _errorEmailState.value = "Invalid email address"
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
        }

        return registerSuccessful
    }

    private fun validateInput(
        name: String,
        email: String,
        password: String
    ): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            _errorNameState.value = "Name cannot be empty"
            isValid = false
        } else if (!name.matches(Regex("^[a-zA-Z ]+$"))) {
            _errorNameState.value = "Name can only consist of alphabet"
            isValid = false
        } else if (name.length < 3 || name.length > 30) {
            _errorNameState.value = "Name must consist of 3-30 characters"
            isValid = false
        } else {
            _errorNameState.value = null
        }

        if (email.isEmpty()) {
            _errorEmailState.value = "Email cannot be empty"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorEmailState.value = "Invalid email address"
            isValid = false
        } else {
            _errorEmailState.value = null
        }

        if (password.isEmpty()) {
            _errorPasswordState.value = "Password cannot be empty"
            isValid = false
        } else if (password.length < 6) {
            _errorPasswordState.value = "Password must consist of at least 6 characters"
            isValid = false
        } else {
            _errorPasswordState.value = null
        }

        return isValid
    }

    private fun clearErrorState() {
        _errorNameState.value = null
        _errorEmailState.value = null
        _errorPasswordState.value = null
    }

}