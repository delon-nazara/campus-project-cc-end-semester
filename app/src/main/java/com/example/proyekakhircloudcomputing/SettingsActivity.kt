package com.example.proyekakhircloudcomputing

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.NavbarButton


class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsScreen(
                navigateToHome = { // Navigasi ke MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun SettingsScreen(navigateToHome: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F2DE)) // Warna latar belakang utama
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 80.dp), // Ruang untuk Navbar
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

            // Gambar profil
            Image(
                painter = painterResource(id = R.drawable.contoh), // Ganti dengan resource gambar Anda
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape) // Bentuk lingkaran
                    .background(Color.Red) // Placeholder jika gambar belum di-load
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username
            Text(
                text = "@arkan",
                fontSize = 18.sp,
                color = Color(0xFFF2A73B)
            )
        }

        // Navbar di bagian bawah
        Navbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onHomeClick = navigateToHome, // Menggunakan navigasi ke MainActivity
            onSettingsClick = { /* Stay on the current page */ }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(navigateToHome = {})
}
