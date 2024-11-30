package com.example.proyekakhircloudcomputing.ui.screen

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.UserModel
import com.google.firebase.firestore.auth.User

@Preview
@Composable
fun HomeScreen(
    userData: UserModel = UserModel(),
    onUserProfileClicked: () -> Unit = {},
    onNotificationIconClicked: () -> Unit = {},
    onHomeButtonClicked: () -> Unit = {},
    onCapsuleButtonClicked: () -> Unit = {},
    onDiscoverButtonClicked: () -> Unit = {},
    onNotificationButtonClicked: () -> Unit = {},
    onSettingButtonClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blue_main))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // ===================================
            // Pemanggil Function Header
            // ===================================
            TopBar(
                userProfileUrl = userData.profileUrl,
                onUserProfileClicked = onUserProfileClicked,
                onNotificationIconClicked = onNotificationIconClicked
            )

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Halo, ${userData.userName}",
                fontSize = 25.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Abadikan momen berharga mu!",
                fontSize = 15.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(50.dp))

            // ===================================
            // Pemanggil Bagian Capsule
            // ===================================
            CapsulesSection(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
        }

        // ===================================
        // Bottom Navigasi Bar
        // ===================================
        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onHomeButtonClicked = onHomeButtonClicked,
            onCapsuleButtonClicked = onCapsuleButtonClicked,
            onDiscoverButtonClicked = onDiscoverButtonClicked,
            onNotificationButtonClicked = onNotificationButtonClicked,
            onSettingButtonClicked = onSettingButtonClicked
        )
    }
}

@Composable
fun CapsulesSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                colorResource(R.color.yellow_background),
                shape = RoundedCornerShape(topStart = 50.dp,topEnd = 50.dp),
            )
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(50.dp),
                    clip = false
                )
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(R.color.orange))
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Kapsul Public",
                fontSize = 20.sp,
                color = colorResource(R.color.yellow_background),
                fontWeight = FontWeight.SemiBold
            )
        }
        // ===================================
        // Pemanggil Lazy Row
        // ===================================
        DynamicLazyRowWithButton()
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize()
                .shadow(
                    elevation = 8.dp, // Tinggi bayangan
                    shape = RoundedCornerShape(50.dp), // Bentuk bayangan mengikuti RoundedCorner
                    clip = false // Tidak memotong bayangan ke dalam batas shape
                )
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(R.color.orange))
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Kapsul Pribadi",
                fontSize = 20.sp,
                color = colorResource(R.color.yellow_background),
                fontWeight = FontWeight.SemiBold
            )
        }
        // ===================================
        // Pemanggil Lazy Row
        // ===================================
        DynamicLazyRowWithButton()
    }
}

// ===================================
// Fungsi Lazy Row
// ===================================
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(50.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(R.color.green_kahf))
                    .clickable {
                        // Tambahkan kapsul baru ke dalam LazyRow
                        capsules.add("Capsule ${capsules.size + 1}")
                    },
                contentAlignment = Alignment.Center
            ) {
                // Tombol "+" untuk menambahkan kapsul ke LazyRow
                Text(
                    text = "+",
                    fontSize = 50.sp,
                    color = Color.White
                )
            }
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Buat Kapsul Baru",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
        //=======================================
        // LazyRow yang menampilkan kapsul
        //=======================================
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(capsules.size) { index ->
                CapsuleItem(title = capsules[index])
            }
        }
    }
}

// ===================================
// Item Capsule
// ===================================
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
            // Konten kapsul (contoh: dummy)
            Image(
                painter = painterResource(id = R.drawable.pp),
                contentDescription = "Image Capsule",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}