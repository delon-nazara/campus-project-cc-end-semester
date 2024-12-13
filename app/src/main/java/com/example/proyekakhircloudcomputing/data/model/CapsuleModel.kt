package com.example.proyekakhircloudcomputing.data.model

data class CapsuleModel(
    val indexCover: Int = 0,
    val title: String = "",
    val description: String = "",
    val type: String = "",
    val createdAt: Long = 0L,
    val lockedAt: Long = 0L,
    val unlockedAt: Long = 0L
)
