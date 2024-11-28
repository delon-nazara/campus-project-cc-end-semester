package com.example.proyekakhircloudcomputing.ui.screen.archive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(
                onLoginClick = { _, _-> /* Tambahkan aksi tombol Masuk di sini */ },
                onSignUpClick = {
                    startActivity(Intent(this, SignUpActivity::class.java))
                },
                onBackClick = { finish() }
            )
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: (String, String) -> Unit, onSignUpClick: () -> Unit, onBackClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
    {
        Image(
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
        ) {
            Text(text = "Masuk", fontSize = 25.sp, color = Color.Black, modifier = Modifier.align(Alignment.CenterHorizontally).padding(all = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onLoginClick(username, password) },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Masuk")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Belum punya akun? Daftar sekarang", fontSize = 15.sp, modifier = Modifier.align(Alignment.CenterHorizontally))

            Button(onClick = onSignUpClick, colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main)), modifier = Modifier.fillMaxWidth()) {
                Text(text = "Daftar")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onBackClick, colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main)), modifier = Modifier.fillMaxWidth()) {
                Text(text = "Kembali")
            }
        }
    }
}
