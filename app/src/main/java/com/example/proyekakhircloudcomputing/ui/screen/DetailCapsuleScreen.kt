package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.cloudinary.android.MediaManager
import com.example.proyekakhircloudcomputing.R
import com.example.proyekakhircloudcomputing.data.model.CapsuleModel
import com.example.proyekakhircloudcomputing.data.source.contributorList
import com.example.proyekakhircloudcomputing.util.convertMillisecondsToLargestUnit

@Preview
@Composable
fun DetailCapsuleScreen(
    capsuleData: CapsuleModel = CapsuleModel(),
    popBackStack: () -> Unit = {},
) {
    val currentTime = System.currentTimeMillis()
    val capsuleStatus: String
    val remainingTime: String

    if (currentTime < capsuleData.lockedAt) {
        capsuleStatus = "Aktif"
        remainingTime =
            "Dikunci dalam ${convertMillisecondsToLargestUnit(capsuleData.lockedAt - currentTime)} lagi"
    } else if (currentTime < capsuleData.unlockedAt) {
        capsuleStatus = "Terkunci"
        remainingTime =
            "Terbuka dalam ${convertMillisecondsToLargestUnit(capsuleData.unlockedAt - currentTime)} lagi"
    } else {
        capsuleStatus = "Terbuka"
        remainingTime =
            "Terbuka sejak ${convertMillisecondsToLargestUnit(currentTime - capsuleData.unlockedAt)} yang lalu"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blue_main))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(top = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.orange), RoundedCornerShape(16.dp))
            ) {
                IconButton(
                    onClick = { popBackStack() },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Tombol kembali",
                        tint = colorResource(R.color.white)
                    )
                }

                Text(
                    text = "Detail Kapsul",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Status: $capsuleStatus",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "($remainingTime)",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
            HorizontalDivider(
                thickness = 3.dp,
                color = colorResource(id = R.color.orange),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(125.dp)
                        .fillMaxHeight()
                        .background(
                            colorResource(R.color.orange),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                colorResource(R.color.yellow_background),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(8.dp), // Kotak dalam berwarna putih dengan rounded corner
                        contentAlignment = Alignment.Center,
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
                            contentDescription = "Gambar Capsule",
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            colorResource(R.color.orange),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                colorResource(R.color.yellow_background),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = capsuleData.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        Text(
                            text = capsuleData.description,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.orange))
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Button(
                    enabled = true,
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.yellow_background),
                        disabledContainerColor = colorResource(R.color.light_orange)
                    ),
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "+  Tambahkan Gambar",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                if (capsuleData.imageId.isEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.image_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.yellow_background),
                            modifier = Modifier
                                .size(100.dp)
                                .padding(bottom = 16.dp)
                        )
                        Text(
                            text = "Tidak ada kenangan yang tersimpan.\nTambahkan sekarang juga!",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.yellow_background),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                } else {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2), // 2 kolom per baris
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalItemSpacing = 8.dp
                    ) {
                        items(capsuleData.imageId) { image ->
                            ImageItem(
                                imageId = image
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            var showContributorList by remember { mutableStateOf(false) }

            Button(
                onClick = { showContributorList = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.yellow_background),
                ),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Lihat Daftar Kontributor",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            if (showContributorList) {
                Dialog(onDismissRequest = { showContributorList = false }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.orange),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = colorResource(R.color.yellow_background),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(start = 24.dp, end = 24.dp, bottom = 12.dp, top = 24.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Daftar Kolaborator",
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(bottom = 20.dp)
                                )

                                contributorList.forEach { contributor ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(CircleShape)
                                                .background(colorResource(id = R.color.light_orange))
                                                .border(
                                                    width = 2.dp,
                                                    color = colorResource(id = R.color.orange),
                                                    shape = CircleShape
                                                )
                                        ) {
                                            Image(
                                                painter = painterResource(id = contributor.imageId),
                                                contentDescription = "Profile Picture for ${contributor.name}",
                                                modifier = Modifier
                                                    .size(35.dp)
                                                    .clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))

                                        Text(
                                            text = contributor.name,
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }

                                TextButton(
                                    onClick = { showContributorList = false },
                                    contentPadding = PaddingValues(vertical = 6.dp, horizontal = 12.dp),
                                    modifier = Modifier.align(Alignment.End)
                                ) {
                                    Text(
                                        text = "Kembali",
                                        color = Color.Black,
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
            ) {
                Text(
                    text = "Bergabung sebagai Kontributor",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ImageItem(imageId: String) {
    Box(
        modifier = Modifier
            .background(colorResource(R.color.yellow_background))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = MediaManager.get().url().generate(imageId),
            contentDescription = "Gallery Image",
            modifier = Modifier.fillMaxSize(),
        )
    }
}