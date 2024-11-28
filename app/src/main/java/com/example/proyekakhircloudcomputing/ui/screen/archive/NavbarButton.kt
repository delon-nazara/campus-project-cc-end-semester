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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R


@Composable
fun Navbar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit
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
            NavbarButton(
                label = "Home",
                iconId = R.drawable.iconhome,
                onClick = onHomeClick // Navigasi ke halaman Home (MainActivity)
            )

            // Tombol Capsule
            NavbarButton(
                label = "Capsule",
                iconId = R.drawable.iconcapsule,
                onClick = { /* TODO: Implement Capsule NavigationComponent */ }
            )

            // Tombol Search
            NavbarButton(
                label = "Search",
                iconId = R.drawable.iconsearch,
                onClick = { /* TODO: Implement Search NavigationComponent */ }
            )

            // Tombol Notifications
            NavbarButton(
                label = "Notiv",
                iconId = R.drawable.iconnotip,
                onClick = { /* TODO: Implement Notifications NavigationComponent */ }
            )

            // Tombol Settings
            NavbarButton(
                label = "Rigged",
                iconId = R.drawable.icongear,
                onClick = onSettingsClick // Tetap di Settings
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