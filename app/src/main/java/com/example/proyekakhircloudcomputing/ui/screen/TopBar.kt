package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.UserModel
import com.example.proyekakhircloudcomputing.data.source.Route

@Preview
@Composable
fun TopBar(
    color: Color = colorResource(R.color.yellow_background),
    userData: UserModel? = UserModel(),
    navigateTo: (String) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 56.dp, bottom = 24.dp)
            .background(color, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Gambar pertama
        Image(
            painter = painterResource(
                when (userData?.firstLetter) {
                    "a" -> R.drawable.alphabet_profile_picture_d
                    "b" -> R.drawable.alphabet_profile_picture_d
                    "c" -> R.drawable.alphabet_profile_picture_d
                    "d" -> R.drawable.alphabet_profile_picture_d
                    else -> R.drawable.alphabet_profile_picture_d
                    // todo
                }
            ),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable {
                    navigateTo(Route.SETTING_SCREEN.name)
                }
        )

        // Gambar kedua (Memoria logo)
        Image(
            painter = painterResource(id = R.drawable.memoria_logo), // Gambar tengah
            contentDescription = "Memoria logo",
            modifier = Modifier.height(28.dp) // Ukuran gambar utama
        )

        // Gambar ketiga (Notifikasi)
        Image(
            painter = painterResource(id = R.drawable.notifications), // Gambar kanan
            contentDescription = "Notification icon",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navigateTo(Route.NOTIFICATION_SCREEN.name)
                }
        )
    }
}