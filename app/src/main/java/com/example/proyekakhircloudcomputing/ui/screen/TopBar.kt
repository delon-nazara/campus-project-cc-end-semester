package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyekakhircloudcomputing.R

@Preview
@Composable
fun TopBar(
    userProfileUrl: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(top =32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(80.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(50.dp),
                        clip = false
                    )
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(colorResource(R.color.yellow_background))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(64.dp), // Jarak antar elemen
                ) {
                    // Gambar pertama
                    AsyncImage(
                        model = userProfileUrl,
                        contentDescription = "Profile picture",
                        placeholder = painterResource(R.drawable.profile_picture_temporary),
                        error = painterResource(R.drawable.profile_picture_temporary),
                        modifier = Modifier.size(40.dp).clip(CircleShape) // Ukuran gambar kecil
                    )
                    // Gambar kedua (Memoria logo)
                    Image(
                        painter = painterResource(id = R.drawable.memoria), // Gambar tengah
                        contentDescription = "Memoria logo",
                        modifier = Modifier.size(100.dp) // Ukuran gambar utama
                    )

                    // Gambar ketiga (Notifikasi)
                    Image(
                        painter = painterResource(id = R.drawable.notifications), // Gambar kanan
                        contentDescription = "Notification icon",
                        modifier = Modifier.size(30.dp) // Ukuran gambar kecil
                    )
                }
            }
        }
    }
}