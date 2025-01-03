package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.UserModel

@Preview
@Composable
fun SettingScreen(
    userData: UserModel? = UserModel(),
    navigateTo: (String) -> Unit = {},
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
                .padding(top = 64.dp, bottom = 80.dp), // Ruang untuk BottomBarOld
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header "Pengaturan"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFF2A73B)) // Warna header
                    .padding(horizontal = 48.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pengaturan",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            // Frame di sekitar gambar profil
            Box(
                modifier = Modifier
                    .size(115.dp) // Ukuran frame sedikit lebih besar dari gambar
                    .clip(RoundedCornerShape(16.dp)) // Membuat sudut frame membulat
                    .background(Color(0xFFF2A73B)), // Warna frame (orange dari colors.xml)
                contentAlignment = Alignment.Center
            ) {
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
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(12.dp))
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Username
            Text(
                text = userData?.fullName ?: "",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onLogoutButtonClicked() },
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp).padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.orange)
                )
            ) {
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
                )
            }
        }

        // BottomBarOld di bagian bawah
        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            navigateTo = navigateTo
        )
    }
}