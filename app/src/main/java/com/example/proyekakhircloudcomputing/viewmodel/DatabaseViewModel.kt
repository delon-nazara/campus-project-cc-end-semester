package com.example.proyekakhircloudcomputing.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cloudinary.android.MediaManager
import com.example.proyekakhircloudcomputing.data.model.CapsuleModel
import com.example.proyekakhircloudcomputing.data.model.UserModel
import com.example.proyekakhircloudcomputing.util.formatName
import com.example.proyekakhircloudcomputing.util.getFirstLetter
import com.example.proyekakhircloudcomputing.util.getFirstWord
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
    private var cloudinaryHasInitialize = false

    fun cloudinaryInitialization(context: Context) {
        if (!cloudinaryHasInitialize) {
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

            cloudinaryHasInitialize = true
        }
    }

    private val database: FirebaseFirestore = Firebase.firestore
    private val userReference = database.collection("user")
    private val capsulesReference = database.collection("capsule")

    private var _userDataState = MutableStateFlow<UserModel?>(null)
    val userDataState: StateFlow<UserModel?> = _userDataState.asStateFlow()

    private var _capsulesState = MutableStateFlow<List<CapsuleModel>?>(null)
    val capsulesState: StateFlow<List<CapsuleModel>?> = _capsulesState.asStateFlow()

    fun addUserToDatabase(
        userAuth: FirebaseUser,
        name: String,
        email: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        showLoading: (Boolean) -> Unit
    ) {
        val cleanName = formatName(name)
        val firstWord = getFirstWord(cleanName)
        val firstLetter = getFirstLetter(cleanName).toString()
        val currentTime = System.currentTimeMillis().toString()

        val newUser = UserModel(
            fullName = cleanName,
            firstWord = firstWord,
            firstLetter = firstLetter,
            email = email,
            createdAt = currentTime
        )

        showLoading(true)
        userReference.document(userAuth.uid)
            .set(newUser)
            .addOnSuccessListener {
                showLoading(false)
                _userDataState.value = newUser
                onSuccess()
            }
            .addOnFailureListener {
                showLoading(false)
                _userDataState.value = null
                onFailure()
                userAuth.delete()
            }
    }

    fun getUserFromDatabase(
        userId: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        showLoading: (Boolean) -> Unit
    ) {
        showLoading(true)
        userReference.document(userId)
            .get()
            .addOnSuccessListener { document ->
                showLoading(false)
                val data = document.data!!
                val userData = UserModel(
                    fullName = data["fullName"].toString(),
                    firstWord = data["firstWord"].toString(),
                    firstLetter = data["firstLetter"].toString(),
                    email = data["email"].toString(),
                    createdAt = data["createdAt"].toString()
                )
                _userDataState.value = userData
                onSuccess()
            }
            .addOnFailureListener {
                showLoading(false)
                onFailure()
            }
    }

    fun getCapsulesFromDatabase() {
        capsulesReference.get()
            .addOnSuccessListener { documents ->
                val listCapsulesData = documents.map { document ->
                    val data = document.data
                    CapsuleModel(
                        type = data["type"].toString(),
                        name = data["name"].toString(),
                        indexCover = data["indexCover"].toString().toInt(),
                        status = data["status"].toString(),
                        lockedAt = data["lockedAt"].toString(),
                        unlockedAt = data["unlockedAt"].toString()
                    )
                }
                _capsulesState.value = listCapsulesData
            }
            .addOnFailureListener {}
    }

    fun clearAllData() {
        _userDataState.value = null
        _capsulesState.value = null
    }

}