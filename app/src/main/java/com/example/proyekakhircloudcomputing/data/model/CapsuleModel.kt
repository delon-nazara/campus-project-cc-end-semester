package com.example.proyekakhircloudcomputing.data.model

data class CapsuleModel(
    val type: String,
    val name: String,
    val indexCover: Int,
    val status: String,
//    val createdAt: String, // todo: late add
    val lockedAt: String, // todo: change to seconds since epoch
    val unlockedAt: String // todo: change to seconds since epoch
)
