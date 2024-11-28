package com.example.proyekakhircloudcomputing.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.cloudinary.android.MediaManager
import com.example.proyekakhircloudcomputing.data.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

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

    private val db: FirebaseFirestore = Firebase.firestore
    private val userRef = db.collection("user")

    fun addUserToDatabase(id: String, name: String, email: String): Boolean {
        var addSuccessful = false
        userRef.document(id)
            .set(
                UserModel(
                    name = name,
                    email = email,
                    profileUrl = "https://res.cloudinary.com/${cloudinaryName}/image/upload/alphabet_profile_picture_${name[0]}"
                )
            )
            .addOnSuccessListener {
                addSuccessful = true
            }
        return addSuccessful
    }

}