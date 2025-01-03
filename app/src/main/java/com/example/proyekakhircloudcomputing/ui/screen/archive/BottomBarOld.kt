package com.example.proyekakhircloudcomputing.ui.screen.archive

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.proyekakhircloudcomputing.R

@Preview
@Composable
fun BottomBarOld(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(50.dp))
            .background(Color(0xFF4A9BB4))
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tombol Home
            BottomBarMenuOld(
                label = "Home",
                iconId = R.drawable.iconhome,
                onClick = onHomeClick // Navigasi ke halaman Home (MainActivity)
            )

            // Tombol Capsule
            BottomBarMenuOld(
                label = "Capsule",
                iconId = R.drawable.iconcapsule,
                onClick = { /* TODO: Implement Capsule MainApp */ }
            )

            // Tombol Search
            BottomBarMenuOld(
                label = "Search",
                iconId = R.drawable.iconsearch,
                onClick = { /* TODO: Implement Search MainApp */ }
            )

            // Tombol Notifications
            BottomBarMenuOld(
                label = "Notiv",
                iconId = R.drawable.iconnotip,
                onClick = { /* TODO: Implement Notifications MainApp */ }
            )

            // Tombol Settings
            BottomBarMenuOld(
                label = "Rigged",
                iconId = R.drawable.icongear,
                onClick = onSettingsClick // Tetap di Settings
            )
        }
    }
}

@Composable
fun BottomBarMenuOld(label: String, iconId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            modifier = Modifier.size(36.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}