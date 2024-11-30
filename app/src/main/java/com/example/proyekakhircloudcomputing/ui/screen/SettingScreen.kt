package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.UserModel

@Preview
@Composable
fun SettingScreen(
    navigateToHome: () -> Unit = {},
    userData: UserModel? = UserModel(),
    onUserProfileClicked: () -> Unit = {},
    onNotificationIconClicked: () -> Unit = {},
    onHomeButtonClicked: () -> Unit = {},
    onCapsuleButtonClicked: () -> Unit = {},
    onDiscoverButtonClicked: () -> Unit = {},
    onNotificationButtonClicked: () -> Unit = {},
    onSettingButtonClicked: () -> Unit = {},
    onLogoutButtonClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F2DE)) // Warna latar belakang utama
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 80.dp), // Ruang untuk BottomBarOld
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header "Pengaturan"
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFF2A73B)) // Warna header
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "PENGATURAN",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Frame di sekitar gambar profil
            Box(
                modifier = Modifier
                    .size(140.dp) // Ukuran frame sedikit lebih besar dari gambar
                    .clip(RoundedCornerShape(16.dp)) // Membuat sudut frame membulat
                    .background(Color(0xFFF2A73B)), // Warna frame (orange dari colors.xml)
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = userData?.profileUrl,
                    contentDescription = "Profile picture",
                    placeholder = painterResource(R.drawable.profile_picture_temporary),
                    error = painterResource(R.drawable.profile_picture_temporary),
                    modifier = Modifier.size(120.dp).clip(RoundedCornerShape(16.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Username
            Text(
                text = "${userData?.fullName}",
                fontSize = 18.sp,
                color = Color(0xFFF2A73B)
            )

            Button(
                onClick = { onLogoutButtonClicked() },
                modifier = Modifier.padding(top = 24.dp).width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue_main)
                )
            ) {
                Text(
                    text = "Logout"
                )
            }
        }

        // BottomBarOld di bagian bawah
        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            onHomeButtonClicked = onHomeButtonClicked,
            onCapsuleButtonClicked = onCapsuleButtonClicked,
            onDiscoverButtonClicked = onDiscoverButtonClicked,
            onNotificationButtonClicked = onNotificationButtonClicked,
            onSettingButtonClicked = onSettingButtonClicked
        )
    }
}