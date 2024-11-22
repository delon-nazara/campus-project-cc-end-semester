package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun NotificationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2A73B))
    ){
        Navbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
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
    Column(
    ){
        //Fungsi Top App Bar
        HeaderSection()
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 32.dp)
                .fillMaxSize()
        ){
         //Fungsi Lazy Column notifikasi
        NotificationList()
        }
    }
}

@Composable
fun NotificationList() {
    // Daftar notifikasi (gunakan MutableStateList agar dinamis)
    val notifications = remember {
        mutableStateListOf(
            Pair(R.drawable.arkan, "Kapsul kamu akan terkunci dalam 1 hari lagi"),
            Pair(R.drawable.arkan, "@auah menambahkan beberapa media di kapsul kamu")
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notifications) { notification ->
            NotificationCard(
                iconRes = notification.first,
                message = notification.second,
                onDismiss = { notifications.remove(notification) } // Menghapus notifikasi
            )
        }
    }
}

@Composable
fun NotificationCard(
    iconRes: Int,
    message: String,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.yellow_background)
        ),
        shape = RoundedCornerShape(35.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gambar notifikasi
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "Notification icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Teks notifikasi
            Text(
                text = message,
                modifier = Modifier.weight(1f), // Mengisi ruang yang tersedia
                fontSize = 14.sp,
                color = Color.Black
            )

            // Tombol Hapus
            IconButton(
                onClick = { onDismiss()}
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Account"
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
fun PreviewNotificationScree(modifier: Modifier = Modifier) {
    NotificationScreen()
}