package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.example.proyekakhircloudcomputing.data.source.Route

@Preview(showBackground = true)
@Composable
fun CapsuleScreen(
    userData: UserModel? = UserModel(),
    capsulesData: List<CapsuleModel>? = null,
    navigateToDetailCapsule: (Long) -> Unit = {},
    navigateTo: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F3E6))
    ) {
        TopBar(
            color = colorResource(R.color.blue_main),
            userData = userData,
            navigateTo = navigateTo
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp)
                .background(Color(0xFFf2a73b), RoundedCornerShape(16.dp))
        ) {
            if (capsulesData != null) {
                Text(
                    text = "Lihat Kapsul Pribadi Kamu Disini!",
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(24.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                ) {
                    items(capsulesData) { capsule ->
                        ImageCard(
                            capsuleData = capsule,
                            navigateToDetailCapsule = navigateToDetailCapsule
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(Color(0xFFf2a73b), RoundedCornerShape(16.dp))
                .clickable {
                    navigateTo(Route.CREATE_CAPSULE_SCREEN.name)
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.add_icon),
                tint = Color.White,
                contentDescription = null
            )
            Text(
                text = "Tambahkan Kapsul",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            navigateTo = navigateTo
        )
    }
}

@Composable
fun ImageCard(
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
                .size(115.dp)
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
            color = Color.White
        )
    }
}