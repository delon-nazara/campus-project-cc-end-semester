package com.example.proyekakhircloudcomputing.viewmodel

import android.content.Context
import android.util.Log
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
import java.text.SimpleDateFormat
import java.util.Locale

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

    private var _addCapsuleSuccess = MutableStateFlow(false)
    val addCapsuleSuccess: StateFlow<Boolean> = _addCapsuleSuccess.asStateFlow()

    private var _detailCapsule = MutableStateFlow<CapsuleModel?>(null)
    val detailCapsule: StateFlow<CapsuleModel?> = _detailCapsule.asStateFlow()

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

    fun addCapsuleToDatabase(
        indexCover: Int,
        title: String,
        description: String,
        type: String,
        lockedAt: String,
        unlockedAt: String,
        onFailure: () -> Unit,
        showLoading: (Boolean) -> Unit
    ) {
        val format = SimpleDateFormat("EEEE, dd MMMM yyyy, HH:mm", Locale("id", "ID"))

        val newCapsule = CapsuleModel(
            indexCover = indexCover,
            title = title,
            description = description,
            type = type,
            createdAt = System.currentTimeMillis(),
            lockedAt = format.parse(lockedAt)?.time ?: 0L,
            unlockedAt = format.parse(unlockedAt)?.time ?: 0L,
            contributor = listOf(_userDataState.value!!.fullName),
            imageId = emptyList()
        )

        showLoading(true)
        capsulesReference
            .add(newCapsule)
            .addOnSuccessListener {
                showLoading(false)
                _detailCapsule.value = newCapsule
                _addCapsuleSuccess.value = true
            }
            .addOnFailureListener {
                showLoading(false)
                onFailure()
                _addCapsuleSuccess.value = false
            }
    }

    fun getCapsulesFromDatabase() {
        capsulesReference
            .addSnapshotListener { snapshots, e ->
                if (e == null) {
                    val capsules: MutableList<CapsuleModel> = mutableListOf()
                    snapshots?.documents?.forEach { document ->
                        document.toObject(CapsuleModel::class.java)?.let { capsules.add(it) }
                    }
                    _capsulesState.value = capsules
                }
            }
    }

    fun findCapsuleInDatabase(
        createdAt: Long,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        capsulesReference
            .whereEqualTo("createdAt", createdAt)
            .get()
            .addOnSuccessListener { documents ->
                documents.documents.forEach { document ->
                    _detailCapsule.value = document.toObject(CapsuleModel::class.java)
                }
                onSuccess()
            }
            .addOnFailureListener {
                onFailure()
            }
    }

    fun resetAddCapsuleSuccess() {
        _addCapsuleSuccess.value = false
    }

    fun clearAllData() {
        _userDataState.value = null
        _capsulesState.value = null
    }

}