package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
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
    navigateTo: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.yellow_background))
    ) {
        val groupedCapsules = remember { mutableStateMapOf<String, List<CapsuleModel>>() }
        val publicCapsules = remember { mutableStateListOf<CapsuleModel>() }

        if (capsulesData != null) {
            groupedCapsules.clear()
            groupedCapsules.putAll(capsulesData.groupBy { it.type })

            publicCapsules.clear()
            publicCapsules.addAll(groupedCapsules["Public"] ?: emptyList())
        }

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFf2a73b),
                        shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
                    )
                    .padding(bottom = 16.dp)
            ) {
                TopBar(
                    userData = userData,
                    navigateTo = navigateTo
                )

                TopCapsuleSection(publicCapsules)
            }

            CapsulePublic(publicCapsules)
        }

        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            navigateTo = navigateTo
        )
    }
}

@Composable
fun CapsulePublic(
    capsuleData: List<CapsuleModel>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Lihat Kapsul Publik Lainnya",
            color = colorResource(R.color.black),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
//            modifier = Modifier.align(Alignment.Start).padding(start = 64.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier.width(350.dp).height(325.dp)
        ) {
            items(capsuleData) { capsule ->
                TopCapsuleCard(capsule)
            }
        }
    }
}

@Composable
fun TopCapsuleSection(
    capsulesData: List<CapsuleModel>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "TOP PUBLIK KAPSUL",
            color = colorResource(R.color.white),
            fontSize = 22.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(capsulesData) { capsule ->
                TopCapsuleCard(capsule)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun TopCapsuleCard(capsuleData: CapsuleModel) {
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
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}