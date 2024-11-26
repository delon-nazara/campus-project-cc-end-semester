package com.example.proyekakhircloudcomputing

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DiscoverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}
//===================
// DISCOVER SCREEN START
//===================
@Composable
fun DiscoverScreen() {
    var isExpanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.yellow_background))
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(){
                    Card(
                        shape = RoundedCornerShape(
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(R.color.orange),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 450.dp,
                                max = if (isExpanded) 750.dp else 450.dp) // Atur tinggi dinamis
                    ) {
                        Column {
                            // Fungsi Top App Bar
                            HeaderSection()

                            Spacer(modifier = Modifier.height(16.dp)) // Spasi antar komponen

                            // Bagian CapsuleSection
                            CapsuleSection(isExpanded = isExpanded)

                            Spacer(modifier = Modifier.height(16.dp)) // Spasi antar komponen

                            // Tombol untuk memperbesar/memperkecil grid

                        }
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                            .fillMaxWidth()
                    ){
                        BoxImageButton(
                            onClick = {
                                isExpanded = !isExpanded
                                Log.d("CapsuleCard", "isExpanded = $isExpanded") // Debugging state
                            },
                            imageRes = if (isExpanded) R.drawable.minimize_button else R.drawable.maximize_button
                        )

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                // Bagian "Top Kapsul"
                TopCapsuleSection()
            }


        // Navbar di bawah
        Navbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            onHomeClick = {
                // Logika saat tombol Home ditekan
                println("Home button clicked")
            },
            onSettingsClick = {
                // Logika saat tombol Settings ditekan
                println("Settings button clicked")
            }
        )
    }
}
//===================
// MAIN SCREEN END
//===================

//======================
//CAPSULE SECTION START
//======================
@Composable
fun CapsuleSection(isExpanded: Boolean) {
    var namaKapsul by remember { mutableStateOf("") }
    var selectedDropdown by remember { mutableStateOf("Aktif") } // Status pilihan dropdown

        //DATA DUMMY UNTUK CAPSULE SECTION YANG DI ATAS< INI BISA DIGANTI DENGAN YANG ADA DI DATABASE
    val aktif = listOf(
        "2024 New Year" to R.drawable.new_year,
        "Makassar" to R.drawable.makassar,
        "Event A" to R.drawable.new_year,
        "Event B" to R.drawable.makassar,
        "Event C" to R.drawable.new_year,
        "Event D" to R.drawable.makassar,
        "Event E" to R.drawable.new_year,
        "Event F" to R.drawable.makassar,
        "Event G" to R.drawable.new_year,
        "Event H" to R.drawable.makassar,
    )
    val terkunci = listOf(
        "2024 New Year" to R.drawable.new_year,
        "Makassar" to R.drawable.makassar,
        "Event A LOCKED" to R.drawable.new_year,
        "Event B LOCKED" to R.drawable.makassar,
        "Event C LOCKED" to R.drawable.new_year,
        "Event D LOCKED" to R.drawable.makassar,
    )

    // Tampilkan sebagian atau semua item berdasarkan state
    val displayedItemsActive = if (isExpanded) aktif else aktif.take(2)
    val displayedItemsNonactive = if (isExpanded) terkunci else terkunci.take(2)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(R.string.publik_kapsul),
                color = colorResource(R.color.white),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search bar + Dropdown
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //SEARCH BAR UNTUK MENCARI KAPSUL
            TextField(
                value = namaKapsul,
                onValueChange = { namaKapsul = it },
                placeholder = {
                    if (namaKapsul.isEmpty()) {
                        Text("Cari Kapsul") // Teks placeholder hanya muncul jika tidak ada teks
                    }
                },
                modifier = Modifier
                    .width(230.dp)
                    .height(50.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(R.color.black),
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp)
            )

            // Dropdown dengan callback untuk mengubah `selectedDropdown`
            DropdownButton(
                selectedText = selectedDropdown,
                onItemSelected = { selectedDropdown = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar kartu berdasarkan pilihan dropdown
        if (selectedDropdown == "Aktif") {
            CapsuleGrid(items = displayedItemsActive) // Tetap menggunakan CapsuleGrid
        } else if (selectedDropdown == "Terkunci") {
            CapsuleGrid(items = displayedItemsNonactive)
            // Tampilkan daftar terkunci jika dibutuhkan
        }
    }
}

@Composable
fun DropdownButton(
    selectedText: String, // Pilihan saat ini
    onItemSelected: (String) -> Unit // Callback untuk item yang dipilih
) {
    var expanded by remember { mutableStateOf(false) } // Status dropdown
    val items = listOf("Aktif", "Terkunci") // Isi dropdown

    Box {
        // Tombol utama dropdown
        Button(onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue_main), // Warna latar belakang tombol
                contentColor = colorResource(R.color.white) // Warna teks tombol
            )
        ){
            Text(selectedText)
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, // Ubah dengan ikon sesuai kebutuhan Anda
                contentDescription = null,
                tint = colorResource(R.color.white),
                modifier = Modifier.size(20.dp)
            )
        }

        // Menu dropdown
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Tutup dropdown saat klik di luar
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(item) // Kirim item yang dipilih ke callback
                        expanded = false // Tutup dropdown
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

@Composable
fun BoxImageButton(onClick: () -> Unit, imageRes: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .width(50.dp) // Ukuran tombol
                .clickable { onClick(

                ) } // Fungsi klik
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Button Image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun CapsuleGrid(items: List<Pair<String, Int>>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Jumlah kolom tetap (misal 2)
        contentPadding = PaddingValues(0.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items) { item ->
            CapsuleCard(title = item.first, imageRes = item.second)
        }
    }
}

@Composable
fun CapsuleCard(title: String, imageRes: Int, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(16.dp)) // Bentuk rounded untuk gambar
            ,
            contentAlignment = Alignment.Center
        ) {
            // Konten kapsul (gambar)
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Image Capsule",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
//======================
//CAPSULE SECTION END
//======================

//=======================
//TOP CAPSULE SECTION START
//=======================
data class CapsuleData(
    val title: String,
    val imageRes: Int
)

@Composable
fun TopCapsuleSection() {
    val capsuleList = listOf(
        CapsuleData("New Year's", R.drawable.new_year),
        CapsuleData("Makassar", R.drawable.makassar),
        CapsuleData("ILKOM", R.drawable.gambar),
        CapsuleData("Arkan", R.drawable.arkan)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TOP KAPSUL",
            color = colorResource(R.color.black),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Scrollable list of cards
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(capsuleList) { capsule ->
                TopCapsuleCard(
                    title = capsule.title,
                    imageRes = capsule.imageRes,
                    onClick = {
                        // Tambahkan aksi jika kartu diklik
                        println("Clicked on ${capsule.title}")
                    }
                )
            }
        }
    }
}


@Composable
fun TopCapsuleCard(title: String, imageRes: Int, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(150.dp)
            .clickable { onClick() } // Tambahkan klik
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(4.dp, colorResource(R.color.orange), RoundedCornerShape(16.dp)), // Tambahkan border
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Image Capsule",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
//=======================
//TOP CAPSULE SECTION END
//=======================



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewDiscoverScreen(modifier: Modifier = Modifier) {
    DiscoverScreen()
}