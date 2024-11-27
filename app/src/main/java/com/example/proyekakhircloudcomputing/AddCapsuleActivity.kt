package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
    var showDialog by remember { mutableStateOf(false) }
    var judulKapsul by remember { mutableStateOf("") }
    var deskripsiKapsul by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
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
                    tint = colorResource(R.color.white) // Warna orange
                )
            }

            // Header "Buat Kapsul"
            Text(
                text = "Buat Kapsul",
                color = colorResource(R.color.white),
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

            Spacer(modifier = Modifier.height(24.dp))

            // TextField untuk Judul
            TextField(
                value = judulKapsul, // Nilai teks
                onValueChange = { judulKapsul = it },
                placeholder = {
                    if (judulKapsul.isEmpty()) {
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
            Spacer(modifier = Modifier.height(5.dp))

            // TextField untuk Deskripsi Kapsul
            TextField(
                value = deskripsiKapsul, // Nilai teks
                onValueChange = { deskripsiKapsul = it },
                placeholder = {
                    if (deskripsiKapsul.isEmpty()) {
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
                    Text(
                        text = "Pribadi",
                        color = colorResource(R.color.gray_text)
                    )
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
                    Text(text ="Publik",
                        color = colorResource(R.color.gray_text)
                    )
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
                        color = colorResource(R.color.gray_text),
                        fontSize = 16.sp
                    )
                    Button(
                        onClick = { /* Aksi tombol Dikunci */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.orange),
                            contentColor = colorResource(R.color.white)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Tombol Dikunci",
                            color = colorResource(R.color.gray_text)
                            )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "DIBUKA",
                        color = colorResource(R.color.gray_text),
                        fontSize = 16.sp
                    )
                    Button(
                        onClick = { /* Aksi tombol Dibuka */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.orange),
                            contentColor = colorResource(R.color.white)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Tombol Dibuka",
                            color = colorResource(R.color.gray_text)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Checkbox dan Teks
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {isChecked = it},
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(R.color.gray_text),  // Warna saat Checkbox dicentang
                        uncheckedColor = colorResource(R.color.gray_text),
                        checkmarkColor = colorResource(R.color.orange)
                    )
                )
                Text(
                    text = "Kunci Kapsul Setelah Dibuat",
                    fontSize = 16.sp,
                    color = colorResource(R.color.gray_text)
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
                                text = "Cari Pengguna",
                                color = colorResource(R.color.gray_text)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Search",
                                tint = colorResource(R.color.orange)
                            )
                        },
                        textStyle = TextStyle(
                            color = colorResource(R.color.white),
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
                            },
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = colorResource(R.color.green_kahf),
                            unfocusedContainerColor = colorResource(R.color.blue_main),
                            focusedContainerColor = colorResource(R.color.blue_main),
                        )
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
                            color = colorResource(R.color.gray_text)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                Text(text = "BUAT")
            }
        }
    }
    // Dialog pop-up
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
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Kapsul Berhasil Dibuat!",
                            color = colorResource(R.color.gray_text),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Image(
                        painter = painterResource(R.drawable.iconcapsule_confirmation),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = { /* gatau */},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.white),
                                contentColor = colorResource(R.color.orange)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Tambahkan Media",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
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
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun AddCapsulePreview() {
    AddCapsuleScreen()
}


