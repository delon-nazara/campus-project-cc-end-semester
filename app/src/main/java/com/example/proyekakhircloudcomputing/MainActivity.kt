package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2A73B))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Header di bagian atas
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))

            // Konten utama (CapsulesSection)
            CapsulesSection(
                modifier = Modifier
                    .weight(1f) // Mengisi ruang di antara Header dan Navbar
                    .fillMaxSize()
            )
        }

        // Navbar di bagian bawah layar
        Navbar(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Menempatkan Navbar di bagian bawah
                .fillMaxWidth()
        )
    }
}


//semua yang di dalam background kuning
@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
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
                    .clip(shape = RoundedCornerShape(16.dp))  // Membuat sudut membulat
                    .background(colorResource(R.color.yellow_background))
                    .padding(16.dp) // Tambahkan padding dalam Box
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(64.dp), // Jarak antar elemen
                ) {
                    // Gambar pertama
                    Image(
                        painter = painterResource(id = R.drawable.pp), // Gambar kiri
                        contentDescription = "Profile picture",
                        modifier = Modifier.size(40.dp) // Ukuran gambar kecil
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

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Halo,Arkan!",
                fontSize = 25.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Abadikan momen berharga mu!",
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}


@Composable
fun CapsulesSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.yellow_background))
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(R.color.orange))
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Kapsul Public",
                fontSize = 20.sp,
                color = Color(0xFFF4F2DE)
            )

        }
        DynamicLazyRowWithButton()
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(R.color.orange))
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Kapsul Pribadi",
                fontSize = 20.sp,
                color = colorResource(R.color.yellow_background)
            )
        }
        DynamicLazyRowWithButton()

    }
}


@Composable
fun DynamicLazyRowWithButton() {
    // State untuk melacak data dinamis di LazyRow
    val capsules = remember { mutableStateListOf("Capsule 1") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tombol "+" untuk menambahkan kapsul ke LazyRow
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.green_kahf))
                .clickable {
                    // Tambahkan kapsul baru ke dalam LazyRow
                    capsules.add("Capsule ${capsules.size + 1}")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                fontSize = 50.sp,
                color = Color.White
            )
        }

        // LazyRow yang menampilkan kapsul
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f) // LazyRow mengambil sisa ruang
        ) {
            items(capsules.size) { index ->
                CapsuleItem(title = capsules[index])
            }
        }
    }
}

@Composable
fun CapsuleItem(title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(150.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.green_kahf))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Konten kapsul (contoh: judul)
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun Navbar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .wrapContentWidth()  // Membuat navbar hanya selebar konten
            .clip(RoundedCornerShape(50.dp)) // Bentuk kapsul lonjong
            .background(colorResource(R.color.blue_main))
            .padding(vertical = 8.dp), // Padding atas-bawah
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly, // Meratakan tombol
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tombol pertama
            NavbarButton(
                label = "Home",
                iconId = R.drawable.iconhome,
                onClick = { /* TODO: Navigate to Home */ }
            )

            // Tombol kedua
            NavbarButton(
                label = "capsule",
                iconId = R.drawable.iconcapsule,
                onClick = { /* TODO: Navigate to Search */ }
            )

            // Tombol ketiga
            NavbarButton(
                label = "search",
                iconId = R.drawable.iconsearch,
                onClick = { /* TODO: Navigate to Add */ }
            )

            // Tombol keempat
            NavbarButton(
                label = "notiv",
                iconId = R.drawable.iconnotip,
                onClick = { /* TODO: Navigate to Notifications */ }
            )

            // Tombol kelima
            NavbarButton(
                label = "rigged",
                iconId = R.drawable.icongear,
                onClick = { /* TODO: Navigate to Profile */ }
            )
        }
    }
}



@Composable
fun NavbarButton(label: String, iconId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            modifier = Modifier.size(36.dp),
            tint = Color.White // Warna ikon
        )
        Spacer(modifier = Modifier.height(4.dp)) // Jarak antara ikon dan teks
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}







@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}