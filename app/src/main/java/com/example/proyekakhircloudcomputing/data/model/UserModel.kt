package com.example.proyekakhircloudcomputing.data.model

import com.google.firebase.firestore.FieldValue

data class UserModel(
    val name: String,
    val email: String,
    val profileUrl: String,
    val createdAt: FieldValue
)
