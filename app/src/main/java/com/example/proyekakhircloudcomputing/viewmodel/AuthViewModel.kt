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

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _userState.value = auth.currentUser
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> "Password must consist of at least 6 characters"
                        is FirebaseAuthInvalidCredentialsException -> "Invalid email address"
                        is FirebaseAuthUserCollisionException -> "Email has been used"
                        else -> "Register error, try again"
                    }
                }
            }
    }

}