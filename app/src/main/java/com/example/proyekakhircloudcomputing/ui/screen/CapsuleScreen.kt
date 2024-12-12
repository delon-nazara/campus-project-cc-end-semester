package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.CapsuleModel
import com.example.proyekakhircloudcomputing.data.model.UserModel
import com.example.proyekakhircloudcomputing.ui.screen.archive.ImageItem

@Preview(showBackground = true)
@Composable
fun CapsuleScreen(
    userData: UserModel? = UserModel(),
    capsulesData: List<CapsuleModel>? = null,
    navigateTo: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F3E6))
    ) {
        TopBar(
            userData = userData,
            navigateTo = navigateTo
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp)
                .padding(top = 32.dp, bottom = 16.dp)
                .background(Color(0xFFf2a73b), RoundedCornerShape(16.dp))
        ) {
            val groupedCapsules = remember { mutableStateMapOf<String, List<CapsuleModel>>() }
            val privateCapsules = remember { mutableStateListOf<CapsuleModel>() }

            if (capsulesData != null) {
                groupedCapsules.clear()
                groupedCapsules.putAll(capsulesData.groupBy { it.type })

                privateCapsules.clear()
                privateCapsules.addAll(groupedCapsules["private"] ?: emptyList())

                Text(
                    text = "Lihat Kapsul Pribadi Kamu Disini!",
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 28.dp).padding(start = 32.dp)
                )

                LazyVerticalGrid(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(privateCapsules) { capsule ->
                        ImageCard(capsule)
                    }
                }
            }
        }
        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            navigateTo = navigateTo
        )
    }
}

@Composable
fun ImageCard(
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
                        5 -> R.drawable.capsule_cover_template_5
                        6 -> R.drawable.capsule_cover_template_6
                        7 -> R.drawable.capsule_cover_template_7
                        8 -> R.drawable.capsule_cover_template_8
                        9 -> R.drawable.capsule_cover_template_9
                        else -> R.drawable.capsule_cover_template_10
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

val sampleImages = listOf(
    ImageItem(
        imageResId = R.drawable.new_year,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = false
    ),
    ImageItem(
        imageResId = R.drawable.new_year,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = true
    ),
    ImageItem(
        imageResId = R.drawable.makassar,
        contributorIconResId = R.drawable.arkan,
        title = "Makassar",
        location = "Makassar",
        isLocked = true
    ),
    ImageItem(
        imageResId = R.drawable.makassar,
        contributorIconResId = R.drawable.arkan,
        title = "Makassar",
        location = "Makassar",
        isLocked = false
    ),
    ImageItem(
        imageResId = R.drawable.capsule,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = false
    ),
    ImageItem(
        imageResId = R.drawable.capsule,
        contributorIconResId = R.drawable.arkan,
        title = "2024 New Year",
        location = "Makassar",
        isLocked = false
    ),

)

data class ImageItem(
//    val imageUrl: String, // Jika gambar menggunakan URL dari Cloud
//    val contributorIconUrl: String, //Jika ikon kontributor menggunakan URL dari Cloud
    val imageResId: Int, // Menggunakan Int untuk referensi ke R.drawable.nama_gambar
    val contributorIconResId: Int, // Juga menggunakan Int untuk ikon kontributor
    val title: String,
    val location: String,
    val isLocked: Boolean

)