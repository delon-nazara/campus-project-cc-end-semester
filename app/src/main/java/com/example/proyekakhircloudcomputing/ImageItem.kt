package com.example.proyekakhircloudcomputing

data class ImageItem(
//    val imageUrl: String, // Jika gambar menggunakan URL dari Cloud
//    val contributorIconUrl: String, //Jika ikon kontributor menggunakan URL dari Cloud
    val imageResId: Int, // Menggunakan Int untuk referensi ke R.drawable.nama_gambar
    val contributorIconResId: Int, // Juga menggunakan Int untuk ikon kontributor
    val title: String,
    val location: String,
    val isLocked: Boolean

)
