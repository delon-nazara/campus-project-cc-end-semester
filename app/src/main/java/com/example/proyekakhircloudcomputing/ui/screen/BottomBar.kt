package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.proyekakhircloudcomputing.data.source.Route

@Preview
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit = {},
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
                onClick = { navigateTo(Route.HOME_SCREEN.name) }
            )

            // Tombol Capsule
            BottomBarMenuOld(
                label = "Capsule",
                iconId = R.drawable.iconcapsule,
                onClick = { navigateTo(Route.CAPSULE_SCREEN.name) }
            )

            // Tombol Discover
            BottomBarMenuOld(
                label = "Discover",
                iconId = R.drawable.iconsearch,
                onClick = { navigateTo(Route.DISCOVER_SCREEN.name) }
            )

            // Tombol Notification
            BottomBarMenuOld(
                label = "Notification",
                iconId = R.drawable.iconnotip,
                onClick = { navigateTo(Route.NOTIFICATION_SCREEN.name) }
            )

            // Tombol Settings
            BottomBarMenuOld(
                label = "Setting",
                iconId = R.drawable.icongear,
                onClick = { navigateTo(Route.SETTING_SCREEN.name) }
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