package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.CapsuleModel
import com.example.proyekakhircloudcomputing.data.model.UserModel

@Preview
@Composable
fun DiscoverScreen(
    userData: UserModel? = UserModel(),
    capsulesData: List<CapsuleModel>? = null,
    navigateToDetailCapsule: (Long) -> Unit = {},
    navigateTo: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.yellow_background))
    ) {
        if (capsulesData != null) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.blue_main),
                            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                        )
                        .padding(bottom = 16.dp)
                ) {
                    TopBar(
                        userData = userData,
                        navigateTo = navigateTo
                    )

                    TopCapsuleSection(
                        capsulesData = capsulesData,
                        navigateToDetailCapsule = navigateToDetailCapsule
                    )
                }

                AllCapsuleSection(
                    capsulesData = capsulesData,
                    navigateToDetailCapsule = navigateToDetailCapsule
                )
            }
        }

        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 24.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            navigateTo = navigateTo
        )
    }
}

@Composable
fun TopCapsuleSection(
    navigateToDetailCapsule: (Long) -> Unit,
    capsulesData: List<CapsuleModel>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Kapsul Publik Pilihan",
            color = colorResource(R.color.white),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(capsulesData) { capsule ->
                CapsuleCard(
                    capsuleData = capsule,
                    navigateToDetailCapsule = navigateToDetailCapsule
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun AllCapsuleSection(
    navigateToDetailCapsule: (Long) -> Unit,
    capsulesData: List<CapsuleModel>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Lihat Kapsul Publik Lainnya",
            color = colorResource(R.color.black),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 36.dp)
                .padding(bottom = 100.dp)
        ) {
            items(capsulesData) { capsule ->
                CapsuleCard(
                    color = Color.Black,
                    capsuleData = capsule,
                    navigateToDetailCapsule = navigateToDetailCapsule
                )
            }
        }
    }
}

@Composable
fun CapsuleCard(
    color: Color = Color.White,
    navigateToDetailCapsule: (Long) -> Unit,
    capsuleData: CapsuleModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    navigateToDetailCapsule(capsuleData.createdAt)
                }
                .size(115.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.orange))
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
                        9 -> R.drawable.capsule_cover_template_9
                        10 -> R.drawable.capsule_cover_template_10
                        11 -> R.drawable.capsule_cover_template_11
                        else -> R.drawable.capsule_cover_template_12
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
            color = color
        )
    }
}