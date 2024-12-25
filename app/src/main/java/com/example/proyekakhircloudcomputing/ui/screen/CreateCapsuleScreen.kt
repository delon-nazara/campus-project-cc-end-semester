package com.example.proyekakhircloudcomputing.ui.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.proyekakhircloudcomputing.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Preview
@Composable
fun CreateCapsuleScreen(
    popBackStack: () -> Unit = {},
    loadingState: Boolean = false,
    addCapsuleSuccess: Boolean = false,
    navigateToDetailScreen: () -> Unit = {},
    dataEmpty: () -> Unit = {},
    addNewCapsule: (Int, String, String, String, String, String) -> Unit = { _, _, _, _, _, _ -> }
) {
    var showCoverOptionModal by remember { mutableStateOf(false) }

    var capsuleCover by remember { mutableStateOf<Int?>(null) }
    var capsuleTitle by remember { mutableStateOf("") }
    var capsuleDescription by remember { mutableStateOf("") }
    var capsuleType by remember { mutableStateOf("Private") }

    val context = LocalContext.current
    var lockedTime by remember { mutableStateOf("Pilih tanggal...") }
    val lockedCalendar = Calendar.getInstance()
    var unlockedTime by remember { mutableStateOf("Pilih tanggal...") }
    val unlockedCalendar = Calendar.getInstance()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blue_main)) // Warna biru sesuai gambar
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    text = "Buat Kapsul",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 8.dp)
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
                    if (capsuleCover == null) {
                        IconButton(
                            onClick = { showCoverOptionModal = !showCoverOptionModal },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "Tambah",
                                tint = colorResource(R.color.orange), // Ikon berwarna orange
                                modifier = Modifier.size(64.dp) // Ukuran ikon
                            )
                        }
                    } else {
                        Image(
                            painter = painterResource(
                                when (capsuleCover) {
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
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                showCoverOptionModal = !showCoverOptionModal
                            }
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

            Text(
                text = "Kapsul dikunci pada:",
                color = colorResource(R.color.gray_text),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.Start)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, RoundedCornerShape(16.dp))
                    .border(BorderStroke(2.dp, Color(0xFFFAB900)))
                    .clickable {
                        val datePicker = DatePickerDialog(
                            context,
                            { _, year, month, day ->
                                lockedCalendar.set(year, month, day)
                                // Tampilkan TimePicker
                                val timePicker = TimePickerDialog(
                                    context,
                                    { _, hour, minute ->
                                        lockedCalendar.set(Calendar.HOUR_OF_DAY, hour)
                                        lockedCalendar.set(Calendar.MINUTE, minute)
                                        lockedTime = SimpleDateFormat(
                                            "EEEE, dd MMMM yyyy, HH:mm",
                                            Locale("id", "id")
                                        ).format(lockedCalendar.time)
                                    },
                                    lockedCalendar.get(Calendar.HOUR_OF_DAY),
                                    lockedCalendar.get(Calendar.MINUTE),
                                    true
                                )
                                timePicker.show()
                            },
                            lockedCalendar.get(Calendar.YEAR),
                            lockedCalendar.get(Calendar.MONTH),
                            lockedCalendar.get(Calendar.DAY_OF_MONTH)
                        )
                        datePicker.show()
                    }
            ) {
                Text(
                    text = lockedTime,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Kapsul dibuka pada:",
                color = colorResource(R.color.gray_text),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.Start)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, RoundedCornerShape(16.dp))
                    .border(BorderStroke(2.dp, Color(0xFFFAB900)))
                    .clickable {
                        val datePicker = DatePickerDialog(
                            context,
                            { _, year, month, day ->
                                unlockedCalendar.set(year, month, day)
                                // Tampilkan TimePicker
                                val timePicker = TimePickerDialog(
                                    context,
                                    { _, hour, minute ->
                                        unlockedCalendar.set(Calendar.HOUR_OF_DAY, hour)
                                        unlockedCalendar.set(Calendar.MINUTE, minute)
                                        unlockedTime = SimpleDateFormat(
                                            "EEEE, dd MMMM yyyy, HH:mm",
                                            Locale("id", "id")
                                        ).format(unlockedCalendar.time)
                                    },
                                    unlockedCalendar.get(Calendar.HOUR_OF_DAY),
                                    unlockedCalendar.get(Calendar.MINUTE),
                                    true
                                )
                                timePicker.show()
                            },
                            unlockedCalendar.get(Calendar.YEAR),
                            unlockedCalendar.get(Calendar.MONTH),
                            unlockedCalendar.get(Calendar.DAY_OF_MONTH)
                        )
                        datePicker.show()
                    }
            ) {
                Text(
                    text = unlockedTime,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Tombol Buat
            Button(
                onClick = {
                    if (capsuleCover != null &&
                        capsuleTitle.isNotEmpty() &&
                        capsuleDescription.isNotEmpty() &&
                        lockedTime != "Pilih tanggal..." &&
                        unlockedTime != "Pilih tanggal..."
                    ) {
                        addNewCapsule(capsuleCover!!, capsuleTitle, capsuleDescription, capsuleType, lockedTime, unlockedTime)
                    } else {
                        dataEmpty()
                    }
                }, // Tampilkan dialog saat tombol ditekan
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.orange),
                    contentColor = colorResource(R.color.white)
                ),
                enabled = !loadingState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Buat Kapsul Baru")
            }
        }
        if (loadingState) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    // Success modal
    if (addCapsuleSuccess) {
        Dialog(onDismissRequest = { navigateToDetailScreen() }) {
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
                            onClick = { navigateToDetailScreen() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.white),
                                contentColor = colorResource(R.color.orange)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Pergi ke Halaman Kapsul",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }

    // Cover option modal
    if (showCoverOptionModal) {
        Dialog(onDismissRequest = { showCoverOptionModal = false }) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(13) { index ->
                        Image(
                            painter = painterResource(
                                when (index) {
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
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                capsuleCover = index
                                showCoverOptionModal = false
                            }
                        )
                    }
                }
            }
        }
    }

}