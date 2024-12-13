package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.proyekakhircloudcomputing.R

//=========================
// Data class contributor
//=========================
data class Contributor(
    val name: String, val profileImage: Int
)

@Preview
@Composable
fun DetailCapsuleScreen() {
    // State untuk menyimpan gambar yang dipilih
    var selectedImage by remember { mutableStateOf<Int?>(null) }

    // State untuk menampilkan dialog
    var showDialog by remember { mutableStateOf(false) }

    // State untuk melacak apakah konfirmasi sudah dilakukan
    var isConfirmed by remember { mutableStateOf(false) }

    // Daftar gambar
    val images = remember {
        mutableStateListOf(
            R.drawable.capsule_cover_template_1,
            R.drawable.capsule_cover_template_2,
            R.drawable.capsule_cover_template_4,
            R.drawable.capsule_cover_template_3,
            R.drawable.capsule_cover_template_5,
            R.drawable.capsule_cover_template_0
        )
    }

    // State untuk menyimpan apakah pop-up daftar kontributor ditampilkan
    var showContributorDialog by remember { mutableStateOf(false) }

    // Daftar kontributor
    val contributors = listOf(
        Contributor(name = "@Imeh", profileImage = R.drawable.arkan),
        Contributor(name = "@Arkan", profileImage = R.drawable.capsule),
        Contributor(name = "@UserC", profileImage = R.drawable.arkan)
    )

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
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.orange), RoundedCornerShape(16.dp))
            ) {
                IconButton(
                    onClick = {},
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
                text = "Status: Terbuka",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "(Dikunci dalam 3 hari lagi)",
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
                            painter = painterResource(id = R.drawable.capsule_cover_template_4),
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
                            text = "Sibayak Hiking",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        Text(
                            text = "Dokumentasi pendakian gunung sibayak bersama teman seperjuangan ini adalah teks tambahan supaya deskripsi menjadi sedikit lebih panjang",
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
                    onClick = { },
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

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2), // 2 kolom per baris
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp
                ) {
                    items(images) { image ->
                        ImageItem(
                            imageRes = image,
                            onClick = { selectedImage = image }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { showContributorDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.yellow_background),
                ),
                shape = RoundedCornerShape(12.dp),
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

            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
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

            if (showContributorDialog) {
                Dialog(onDismissRequest = { showContributorDialog = false }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.orange),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //=================================
                            // List atau daftar kontributor
                            //=================================
                            Text(
                                text = "DAFTAR KOLABORASI",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            contributors.forEach { contributor ->
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
                                        // Gambar profil pengguna
                                        Image(
                                            painter = painterResource(id = contributor.profileImage),
                                            contentDescription = "Profile Picture for ${contributor.name}",
                                            modifier = Modifier
                                                .size(46.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(16.dp))

                                    // Teks nama pengguna
                                    Text(
                                        text = contributor.name,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            TextButton(
                                onClick = { showContributorDialog = false },
                                modifier = Modifier.align(Alignment.Start)
                            ) {
                                Text(
                                    text = "<  Kembali",
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }

            if (selectedImage != null) {
                Dialog(onDismissRequest = { selectedImage = null }) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = selectedImage!!),
                            contentDescription = "Fullscreen Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clickable { selectedImage = null },
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(text = "Yakin ingin bergabung sebagai kontributor?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                isConfirmed = true
                            },
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
                        ) {
                            Text("Ya", color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(Color.Gray)
                        ) {
                            Text("Tidak", color = Color.White)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ImageItem(imageRes: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(colorResource(R.color.yellow_background))
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Gallery Image",
            modifier = Modifier.fillMaxSize(),
        )
    }
}