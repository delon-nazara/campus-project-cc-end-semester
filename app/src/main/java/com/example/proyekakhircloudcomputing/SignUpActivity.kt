package com.example.proyekakhircloudcomputing

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SignUpScreen(
                onSignUpClick = {  _, _->/* Tambahkan aksi tombol Daftar di sini */ },
                onLoginClick = {
                    startActivity(Intent(this, LoginActivity::class.java))
                },
                onBackClick = { finish() }
            )
        }
    }
}

@Composable
fun SignUpScreen(onSignUpClick: (String, String) -> Unit, onLoginClick: () -> Unit, onBackClick: () -> Unit) {
    var fullName = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    Image(
        painter = painterResource(id = R.drawable.title),
        contentDescription = null,
        modifier = Modifier.size(250.dp)
    )
    Box(
        modifier = Modifier
        .fillMaxSize()
        .background(colorResource(R.color.yellow_background))
        .padding(16.dp)

    )
    {   Image(
        painter = painterResource(id = R.drawable.title),
        contentDescription = null,
        modifier = Modifier
            .size(250.dp)
            .align(Alignment.TopCenter)
    )
            Column(
                modifier = Modifier
                    .shadow(3.dp, shape = RoundedCornerShape(16.dp))
                    .border(3.dp, Color.White, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .wrapContentWidth()
                    .align(alignment = Alignment.Center)
                    .background(colorResource(R.color.orange))
                    .padding(16.dp),
            )
            {

                Text(text = "Daftar", fontSize = 25.sp, color = Color.Black, modifier = Modifier.align(Alignment.CenterHorizontally).padding(all = 16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = fullName.value,
                    onValueChange = { fullName.value = it },
                    label = { Text("Nama") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(

                    onClick = { onSignUpClick(email.value, password.value) },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Daftar")
                }
                Spacer(modifier = Modifier.height(8.dp)

                )

                Text(text = "Sudah punya akun? Masuk sekarang", color = Color.Black, fontSize = 15.sp, modifier = Modifier.align(Alignment.CenterHorizontally))

                Button(onClick = onLoginClick, modifier = Modifier.fillMaxWidth(),colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main))) {
                    Text(text = "Masuk")
                }
                Spacer(modifier = Modifier.height(8.dp)
                 )
                Button(onClick = onBackClick, modifier = Modifier.fillMaxWidth(),colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main))) {
                    Text(text = "Kembali")
                }
            }
    }
}
