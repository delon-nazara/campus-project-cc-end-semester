package com.example.proyekakhircloudcomputing.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.cloudinary.android.MediaManager
import com.example.proyekakhircloudcomputing.data.model.UserModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DatabaseViewModel : ViewModel() {

    private lateinit var cloudinaryName: String
    private lateinit var cloudinaryPreset: String

    fun cloudinaryInitialization(context: Context) {
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env"
        }

        cloudinaryName = dotenv["CLOUD_NAME"]
        cloudinaryPreset = dotenv["UPLOAD_PRESET_UNSIGNED"]

        val config = hashMapOf(
            "cloud_name" to cloudinaryName,
            "secure" to true
        )
        MediaManager.init(context, config)
    }

    private val database: FirebaseFirestore = Firebase.firestore
    private val userReference = database.collection("user")

    private var _userDataState = MutableStateFlow<UserModel?>(null)
    val userDataState: StateFlow<UserModel?> = _userDataState.asStateFlow()

    fun addUserToDatabase(
        userAuth: FirebaseUser,
        name: String,
        email: String,
        onSuccess: () -> Unit
    ) {
        val newUser = UserModel(
            name = name,
            email = email,
            profileUrl = "https://res.cloudinary.com/${cloudinaryName}/image/upload/alphabet_profile_picture_${name[0]}"
        )
        userReference.document(userAuth.uid)
            .set(newUser)
            .addOnSuccessListener {
                _userDataState.value = newUser
                onSuccess()
            }
            .addOnFailureListener {
                _userDataState.value = null
                userAuth.delete()
            }
    }

}