package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.proyekakhircloudcomputing.R

@Preview
@Composable
fun CreateCapsuleScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var capsuleTitle by remember { mutableStateOf("") }
    var capsuleDescription by remember { mutableStateOf("") }
    var capsuleType by remember { mutableStateOf("Private") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blue_main)) // Warna biru sesuai gambar
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Tombol Kembali
                IconButton(
                    onClick = { /* Navigasi kembali */ },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .background(colorResource(R.color.orange), CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Kembali",
                        tint = colorResource(R.color.white) // Warna orange
                    )
                }

                // Header "Buat Kapsul"
                Text(
                    text = "Buat Kapsul",
                    color = colorResource(R.color.white),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(
                            colorResource(R.color.orange), // Warna orange
                            RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 30.dp, vertical = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Ikon Tambah
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        colorResource(R.color.orange),
                        RoundedCornerShape(16.dp)
                    ), // Kotak luar berwarna orange dengan rounded corner
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(85.dp)
                        .background(
                            colorResource(R.color.yellow_background),
                            RoundedCornerShape(10.dp)
                        ), // Kotak dalam berwarna putih dengan rounded corner
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.fillMaxSize()
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

            Spacer(modifier = Modifier.height(20.dp))

            // TextField untuk Judul
            TextField(
                value = capsuleTitle, // Nilai teks
                onValueChange = { capsuleTitle = it },
                placeholder = {
                    if (capsuleTitle.isEmpty()) {
                        Text(
                            text = "Judul Kapsul",
                            color = colorResource(R.color.gray_text),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        ) // Teks placeholder hanya muncul jika tidak ada teks
                    }
                },
                textStyle = TextStyle(
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Latar belakang transparan
                    .drawBehind {
                        drawLine(
                            color = Color(0xFFFFA500), //maaf tidak bisa menggunakan string color, gatau kenapa hehe
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 8.dp.toPx()
                        )
                    },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(R.color.green_kahf),
                    unfocusedContainerColor = colorResource(R.color.blue_main),
                    focusedContainerColor = colorResource(R.color.blue_main),
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            // TextField untuk Deskripsi Kapsul
            TextField(
                value = capsuleDescription, // Nilai teks
                onValueChange = { capsuleDescription = it },
                placeholder = {
                    if (capsuleDescription.isEmpty()) {
                        Text(
                            text = "Deskripsi Kapsul",
                            color = colorResource(R.color.gray_text),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        ) // Teks placeholder hanya muncul jika tidak ada teks
                    }
                },
                textStyle = TextStyle(
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Latar belakang transparan
                    .drawBehind {
                        drawLine(
                            color = Color(0xFFFFA500), //maaf tidak bisa menggunakan string color, gatau kenapa hehe
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 8.dp.toPx()
                        )
                    },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(R.color.green_kahf),
                    unfocusedContainerColor = colorResource(R.color.blue_main),
                    focusedContainerColor = colorResource(R.color.blue_main),
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Pilihan Pribadi dan Publik
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { capsuleType = "Private" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when (capsuleType) {
                            "Private" -> colorResource(R.color.orange)
                            else -> Color.Transparent
                        },
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFAB900)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Pribadi",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.gray_text)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { capsuleType = "Public" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when (capsuleType) {
                            "Public" -> colorResource(R.color.orange)
                            else -> Color.Transparent
                        },
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFAB900)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Publik",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.gray_text)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Kapsul dikunci pada:",
                    color = colorResource(R.color.gray_text),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Button(
                    onClick = { /* Aksi tombol Dikunci */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(R.color.white)
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFAB900)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Pilih tanggal",
                        color = colorResource(R.color.gray_text),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Kapsul dibuka pada:",
                    color = colorResource(R.color.gray_text),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Button(
                    onClick = { /* Aksi tombol Dibuka */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(R.color.white)
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFAB900)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Pilih tanggal",
                        color = colorResource(R.color.gray_text),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Tombol Buat
            Button(
                onClick = { showDialog = true }, // Tampilkan dialog saat tombol ditekan
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.orange),
                    contentColor = colorResource(R.color.white)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Buat Kapsul Baru")
            }
        }
    }

    // Dialog pop-up
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.orange),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(30.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Kapsul Berhasil Dibuat!",
                            color = colorResource(R.color.gray_text),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Image(
                        painter = painterResource(R.drawable.iconcapsule_confirmation),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.white),
                                contentColor = colorResource(R.color.orange)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Kembali",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}