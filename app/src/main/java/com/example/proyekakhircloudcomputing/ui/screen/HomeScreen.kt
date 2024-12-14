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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.proyekakhircloudcomputing.data.source.Route

@Preview
@Composable
fun HomeScreen(
    userData: UserModel? = UserModel(),
    publicCapsules: List<CapsuleModel>? = null,
    privateCapsules: List<CapsuleModel>? = null,
    navigateToDetailCapsule: (Long) -> Unit = {},
    navigateTo: (String) -> Unit = {},
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
                color = colorResource(R.color.yellow_background),
                userData = userData,
                navigateTo = navigateTo
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Halo, ${userData?.firstWord}",
                fontSize = 22.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Abadikan momen berhargamu!",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))

            CapsulesSection(
                privateCapsules = privateCapsules,
                publicCapsules = publicCapsules,
                navigateToDetailCapsule = navigateToDetailCapsule,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                navigateTo = navigateTo
            )
        }

        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            navigateTo = navigateTo
        )
    }
}

@Composable
fun CapsulesSection(
    publicCapsules: List<CapsuleModel>?,
    privateCapsules: List<CapsuleModel>?,
    navigateToDetailCapsule: (Long) -> Unit,
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                colorResource(R.color.yellow_background),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            )
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(50.dp),
                    clip = false,
                )
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(R.color.orange))
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Kapsul Pribadi",
                fontSize = 16.sp,
                color = colorResource(R.color.yellow_background),
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        if (privateCapsules != null) {
            DynamicLazyRowWithButton(
                capsulesData = privateCapsules,
                navigateToDetailCapsule = navigateToDetailCapsule
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 30.dp)
                    .size(120.dp)
                    .background(
                        colorResource(R.color.orange),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        navigateTo(Route.CREATE_CAPSULE_SCREEN.name)
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(105.dp)
                        .background(
                            colorResource(R.color.yellow_background),
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Tambah",
                        tint = colorResource(R.color.orange), // Ikon berwarna orange
                        modifier = Modifier.size(64.dp) // Ukuran ikon
                    )
                }
            }
        }

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
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Kapsul Public",
                fontSize = 16.sp,
                color = colorResource(R.color.yellow_background),
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        if (publicCapsules != null) {
            DynamicLazyRowWithButton(
                capsulesData = publicCapsules,
                navigateToDetailCapsule = navigateToDetailCapsule
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 30.dp)
                    .size(120.dp)
                    .background(
                        colorResource(R.color.orange),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        navigateTo(Route.CREATE_CAPSULE_SCREEN.name)
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(105.dp)
                        .background(
                            colorResource(R.color.yellow_background),
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Tambah",
                        tint = colorResource(R.color.orange), // Ikon berwarna orange
                        modifier = Modifier.size(64.dp) // Ukuran ikon
                    )
                }
            }
        }
    }
}

@Composable
fun DynamicLazyRowWithButton(
    navigateToDetailCapsule: (Long) -> Unit,
    capsulesData: List<CapsuleModel>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(capsulesData) { capsule ->
                CapsuleItem(
                    capsuleData = capsule,
                    navigateToDetailCapsule = navigateToDetailCapsule
                )
            }
        }
    }
}

@Composable
fun CapsuleItem(
    navigateToDetailCapsule: (Long) -> Unit,
    capsuleData: CapsuleModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            navigateToDetailCapsule(capsuleData.createdAt)
        }
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.green_kahf))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    when (capsuleData.indexCover) {
                        0 -> R.drawable.capsule_cover_template_0
                        1 -> R.drawable.capsule_cover_template_1
                        2 -> R.drawable.capsule_cover_template_2
                        3 -> R.drawable.capsule_cover_template_3
                        4 -> R.drawable.capsule_cover_template_4
                        5 -> R.drawable.capsule_cover_template_5
                        6 -> R.drawable.capsule_cover_template_6
                        7 -> R.drawable.capsule_cover_template_7
                        8 -> R.drawable.capsule_cover_template_8
                        else -> R.drawable.capsule_cover_template_9
                    }
                ),
                contentDescription = "Image Capsule",
                modifier = Modifier.fillMaxSize(),
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = capsuleData.title,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}