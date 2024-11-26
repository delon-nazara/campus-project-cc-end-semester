package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class DetailCapsuleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

//=========================
// Data class contributor
//=========================
data class Contributor(
    val name: String, val profileImage: Int
)

@Composable
fun DetailCapsuleScreen() {
    // State untuk menyimpan gambar yang dipilih
    var selectedImage by remember { mutableStateOf<Int?>(null) }

    // State untuk menampilkan dialog
    var showDialog by remember { mutableStateOf(false) }

    // State untuk melacak apakah konfirmasi sudah dilakukan
    var isConfirmed by remember { mutableStateOf(false) }

    // Daftar gambar
    val images = remember { mutableStateListOf(
        R.drawable.capsule,
        R.drawable.arkan,
        R.drawable.arkan,
        R.drawable.capsule
    ) }

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
        Image(
            painter = painterResource(id = R.drawable.background_garis_putih),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                //=================================================================
                // tombol kembali ke halaman sebelumnya
                //=================================================================
                IconButton(
                    onClick = {
                        // Tambahkan aksi di sini
                    },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.yellow_back),
                        contentDescription = "Tombol kembali",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Unspecified
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp)
                    .background(
                        color = colorResource(R.color.orange),
                        shape = RoundedCornerShape(25)
                    ),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Apa ya??",
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 10.dp)
                        .weight(1f)
                        .background(
                            color = colorResource(R.color.light_orange),
                            shape = RoundedCornerShape(25)
                        ),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Pribadi",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .padding(end = 10.dp)
                        .weight(1f)
                        .background(
                            color = colorResource(R.color.light_orange),
                            shape = RoundedCornerShape(25)
                        ),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "3 bulan lagi",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
            //=================================================================
            // Gambar Capsule utama
            //=================================================================
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 90.dp, vertical = 5.dp)
                        .background(
                            colorResource(id = R.color.orange),
                            shape = RoundedCornerShape(25.dp)
                        )
                        .border(
                            4.dp,
                            colorResource(id = R.color.orange),
                            shape = RoundedCornerShape(25.dp)
                        )
                        .clip(RoundedCornerShape(25.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.capsule),
                            contentDescription = "Gambar Capsule",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
            //=======================
            // Owner Capsule
            //=======================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.orange))
                        .border(4.dp, colorResource(id = R.color.orange), CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arkan),
                        contentDescription = "Profil akun",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = "@Arkan",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            //=============================================
            // Button untuk menampilkan list contributor
            //=============================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                TextButton(
                    onClick = { showContributorDialog = true }
                ) {
                    Text(
                        text = "${contributors.size} Kontributor  >",
                        color = Color.White
                    )
                }
            }

            //==============================
            // Pop up list kontributor
            //==============================
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
            //=====================
            // Deskripsi kapsul
            //=====================
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(R.string.TextDummyDetailCapsule),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 30.dp),
                    color = Color.White
                )
            }
            HorizontalDivider(
                thickness = 3.dp,
                color = colorResource(id = R.color.orange),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            )
            //=================================================================
            // LazyVeticalGrid untuk menampung capsule yang telah ada di list
            //=================================================================
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 kolom per baris
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(top = 10.dp)
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(images) { image ->
                    ImageItem(
                        imageRes = image,
                        onClick = { selectedImage = image }
                    )
                }
            }
            //==============================================================
            // Untuk pop up fullscreen image ketika capsule di tekan
            //==============================================================
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
            //============================================
            // Button kontributor atau tambahkan media
            //============================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        if (!isConfirmed) {
                            showDialog = true // Tampilkan dialog konfirmasi
                        } else {
                            //=================================================================================
                            // Untuk menambahkan capsule tapi karena gk tau cara buat menu tambah capsule
                            // jadinya aku cuma add gambar biasa aja untuk dummy
                            //=================================================================================
                            images.add(R.drawable.capsule)
                        }
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
                ) {
                    Text(
                        text = if (isConfirmed) "Tambahkan media" else "Bergabung sebagai Kontributor",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            //======================================
            // Dialog konfirmasi pop up
            //=======================================
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
//======================================
// Capsule item
//=======================================
@Composable
fun ImageItem(imageRes: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.orange))
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Gallery Image",
            modifier = Modifier
                .fillMaxSize()
                .size(100.dp)
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailPreview() {
    DetailCapsuleScreen()
}