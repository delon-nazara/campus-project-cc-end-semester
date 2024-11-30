package com.example.proyekakhircloudcomputing.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
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

    private var _errorAllState = MutableStateFlow<String?>(null)
    val errorAllState: StateFlow<String?> = _errorAllState.asStateFlow()

    private var _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    fun register(
        name: String,
        email: String,
        password: String,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: () -> Unit
    ) {
        if (isNameInputValid(name) && isEmailInputValid(email) && isPasswordInputValid(password)) {
           showLoading(true)
            authentication.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    showLoading(false)
                    _userAuthState.value = result.user
                    onSuccess(result.user!!)
                    clearErrorState()
                }
                .addOnFailureListener { exception ->
                    showLoading(false)
                    _userAuthState.value = null
                    when (exception) {
                        is FirebaseAuthUserCollisionException -> {
                            _errorEmailState.value = "Email has been used"
                        }
                        else -> {
                            onFailure()
                        }
                    }
                }
        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit
    ) {
        if (isEmailInputValid(email) && isPasswordInputValid(password)) {
           showLoading(true)
            authentication.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    showLoading(false)
                    _userAuthState.value = result.user
                    clearErrorState()
                    onSuccess(result.user!!.uid)
                }
                .addOnFailureListener { exception ->
                    showLoading(false)
                    _userAuthState.value = null
                    when (exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            _errorAllState.value = "Wrong email or password"
                        }
                        else -> {
                            onFailure()
                        }
                    }
                }
        }
    }

    fun logout() {
        authentication.signOut()
        _userAuthState.value = null
        showLoading(false)
        clearErrorState()
    }

    fun showLoading(state: Boolean) {
        _loadingState.value = state
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
        _errorAllState.value = null
        _errorNameState.value = null
        _errorEmailState.value = null
        _errorPasswordState.value = null
    }

}