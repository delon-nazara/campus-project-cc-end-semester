package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


class CapsuleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomePage(
                images = sampleImages
            )

        }
    }
}
@Composable
fun HomePage(images: List<ImageItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F3E6)) // Background sesuai dengan gambar
    ) {
        // Header Section
        HeaderSection()

        // Judul
        Text(
            text = "LIHAT KAPSUL KAMU\nDISINI",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        // Box Oranye untuk semua kartu
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.9f)
                .background(Color(0xFFFf2a73b), RoundedCornerShape(16.dp))
                .padding(16.dp)
                .weight(1f)
                .align(Alignment.CenterHorizontally)

        ) {
            // LazyColumn untuk list dalam grid 2 kolom
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(images.chunked(2)) { rowImages -> // Memasukkan 2 item per baris
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowImages.forEach { image ->
                            ImageCard(image, Modifier.weight(1f)) // Membagi kolom sama rata
                        }
                    }
                }
            }
        }
        Navbar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onHomeClick = {
                // Logika saat tombol Home ditekan
                println("Home button clicked")
            },
            onSettingsClick = {
                // Logika saat tombol Settings ditekan
                println("Settings button clicked")
            }
        )
    }
}

@Composable
fun ImageCard(item: ImageItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Membuat gambar kotak
                .clip(RoundedCornerShape(8.dp))
        ) {
            // Gambar utama
            Image(
                painter = painterResource(id = item.imageResId), // Menggunakan painterResource untuk drawable
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .let { if (item.isLocked) it.alpha(0.5f) else it }
            )

            // Icon kontributor di kiri atas
            Image(
                painter = painterResource(id = item.contributorIconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .align(Alignment.TopStart)
                    .padding(4.dp)
            )

            // Ikon kunci jika gambar terkunci
            if (item.isLocked) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Locked",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                )
            }
        }

        // Judul di bawah gambar
        Column(
            modifier = Modifier.padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
//            Text(
//                text = item.location,
//                style = MaterialTheme.typography.titleLarge,
//                color = Color.Gray
//            )
        }
    }
}


val sampleImages = listOf(
    ImageItem(
        imageResId = R.drawable.new_year,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = false
    ),
    ImageItem(
        imageResId = R.drawable.new_year,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = true
    ),
    ImageItem(
        imageResId = R.drawable.makassar,
        contributorIconResId = R.drawable.arkan,
        title = "Makassar",
        location = "Makassar",
        isLocked = true
    ),
    ImageItem(
        imageResId = R.drawable.makassar,
        contributorIconResId = R.drawable.arkan,
        title = "Makassar",
        location = "Makassar",
        isLocked = false
    ),
    ImageItem(
        imageResId = R.drawable.capsule,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = false
    ),
    ImageItem(
        imageResId = R.drawable.capsule,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = false
    ),

)


@Preview(showBackground = true)
@Composable
fun PreviewCapsuleActivity() {
    HomePage(sampleImages)
}

