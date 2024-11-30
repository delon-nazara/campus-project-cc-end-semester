package com.example.proyekakhircloudcomputing.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.CapsuleModel
import com.example.proyekakhircloudcomputing.data.model.UserModel

@Preview
@Composable
fun HomeScreen(
    userData: UserModel = UserModel(),
    capsulesData: List<CapsuleModel>? = null,
    onUserProfileClicked: () -> Unit = {},
    onNotificationIconClicked: () -> Unit = {},
    onHomeButtonClicked: () -> Unit = {},
    onCapsuleButtonClicked: () -> Unit = {},
    onDiscoverButtonClicked: () -> Unit = {},
    onNotificationButtonClicked: () -> Unit = {},
    onSettingButtonClicked: () -> Unit = {},
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

            CapsulesSection(
                capsulesData = capsulesData,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
        }

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

@Composable
fun CapsulesSection(
    capsulesData: List<CapsuleModel>?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                colorResource(R.color.yellow_background),
                shape = RoundedCornerShape(topStart = 50.dp,topEnd = 50.dp),
            )
    ) {
        val groupedCapsules = remember { mutableStateMapOf<String, List<CapsuleModel>>() }
        val publicCapsules = remember { mutableStateListOf<CapsuleModel>() }
        val privateCapsules = remember { mutableStateListOf<CapsuleModel>() }

        LaunchedEffect(capsulesData) {
            if (capsulesData != null) {
                groupedCapsules.clear()
                groupedCapsules.putAll(capsulesData.groupBy { it.type })

                publicCapsules.clear()
                publicCapsules.addAll(groupedCapsules["public"] ?: emptyList())

                privateCapsules.clear()
                privateCapsules.addAll(groupedCapsules["private"] ?: emptyList())
            } else {
                groupedCapsules.clear()
                publicCapsules.clear()
                privateCapsules.clear()
            }
        }

        if (capsulesData != null) {
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
            DynamicLazyRowWithButton(publicCapsules)

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
                    text = "Kapsul Pribadi",
                    fontSize = 20.sp,
                    color = colorResource(R.color.yellow_background),
                    fontWeight = FontWeight.SemiBold
                )
            }
            DynamicLazyRowWithButton(privateCapsules)
        }
    }
}

@Composable
fun DynamicLazyRowWithButton(
    capsulesData: List<CapsuleModel>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(capsulesData) { capsule ->
                CapsuleItem2(capsule)
            }
        }
    }
}

@Composable
fun CapsuleItem2(
    capsuleData: CapsuleModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(150.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.green_kahf))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    when (capsuleData.indexCover) {
                        1 -> R.drawable.capsule_cover_template_1
                        2 -> R.drawable.capsule_cover_template_2
                        3 -> R.drawable.capsule_cover_template_3
                        4 -> R.drawable.capsule_cover_template_4
                        else -> R.drawable.capsule_cover_template_5
                    }
                ),
                contentDescription = "Image Capsule",
                modifier = Modifier.fillMaxSize(),
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = capsuleData.name,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}