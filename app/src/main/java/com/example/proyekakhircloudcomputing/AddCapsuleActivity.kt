package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AddCapsuleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddCapsuleScreen()
        }
    }
}

@Composable
fun AddCapsuleScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blue_main)) // Warna biru sesuai gambar
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tombol Kembali
            IconButton(
                onClick = { /* Navigasi kembali */ },
                modifier = Modifier
                    .align(Alignment.Start)
                    .background(colorResource(R.color.orange), CircleShape)
                    .clickable{}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Kembali",
                    tint = Color.White // Warna orange
                )
            }

            // Header "Buat Kapsul"
            Text(
                text = "Buat Kapsul",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier
                    .background(
                        colorResource(R.color.orange), // Warna orange
                        RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ikon Tambah
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(colorResource(R.color.orange), RoundedCornerShape(16.dp)), // Kotak luar berwarna orange dengan rounded corner
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(85.dp)
                        .background(colorResource(R.color.yellow_background), RoundedCornerShape(10.dp)), // Kotak dalam berwarna putih dengan rounded corner
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Tambah",
                        tint = colorResource(R.color.orange), // Ikon berwarna orange
                        modifier = Modifier.size(64.dp) // Ukuran ikon
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // TextField untuk Judul
            TextField(
                value = "", // Nilai teks
                onValueChange = { /* Handle perubahan teks */ },
                placeholder = {
                    Text(
                        text = "Judul Kapsul",
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Latar belakang transparan
                    .drawBehind {
                        drawLine(
                            color = Color(0xFFFFA500),
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 8.dp.toPx()
                        )
                    },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))

            // TextField untuk Deskripsi Kapsul
            TextField(
                value = "", // Nilai teks awal
                onValueChange = { /* Handle perubahan teks */ },
                placeholder = {
                    Text(
                        text = "Deskripsi Kapsul",
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Latar belakang transparan
                    .drawBehind {
                        drawLine(
                            color = Color(0xFFFFA500),
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 8.dp.toPx()
                        )
                    },
                singleLine = false // Izinkan input multi-baris
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Pilihan Pribadi dan Publik
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFFFAB900)
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFAB900)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pribadi")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFFFAB900)
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFAB900)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Publik")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Teks "Dikunci" dan "Dibuka"
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "DIKUNCI",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Button(
                        onClick = { /* Aksi tombol Dikunci */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.orange),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Tombol Dikunci")
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "DIBUKA",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Button(
                        onClick = { /* Aksi tombol Dibuka */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.orange),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Tombol Dibuka")
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Checkbox dan Teks
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = false, onCheckedChange = { })
                Text(
                    text = "Kunci Kapsul Setelah Dibuat",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Field Search dan Daftar Kolaborasi dalam satu Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Background transparan untuk keseluruhan Box
                    .padding(8.dp)
                    .border(2.dp, Color(0xFFFAB900), RoundedCornerShape(8.dp))
            ) {
                Column {
                    // Field Search Pengguna
                    TextField(
                        value = TextFieldValue(""),
                        onValueChange = { },
                        placeholder = {
                            Text(
                                text = "Search Pengguna",
                                color = Color.Gray
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Search",
                                tint = Color(0xFFFAB900)
                            )
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent) // Background transparan untuk Search
                            .drawBehind {
                                drawLine(
                                    color = Color(0xFFFFA500), // Warna garis oranye
                                    start = Offset(0f, size.height), // Mulai dari ujung kiri bawah
                                    end = Offset(size.width, size.height), // Sampai ujung kanan bawah
                                    strokeWidth = 8.dp.toPx() // Ketebalan garis
                                )
                            }
                    )


                    // Daftar Kolaborasi
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color.Transparent, RoundedCornerShape(8.dp)), // Background transparan untuk Daftar Kolaborasi
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "DAFTAR KOLABORASI",
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Buat
            Button(
                onClick = { /* Aksi buat */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAB900),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "BUAT")
            }
        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun AddCapsulePreview() {
    AddCapsuleScreen()
}