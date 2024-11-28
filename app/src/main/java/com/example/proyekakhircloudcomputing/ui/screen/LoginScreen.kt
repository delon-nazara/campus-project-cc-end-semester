package com.example.proyekakhircloudcomputing.ui.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R

@Preview
@Composable
fun LoginScreen(
    onLoginButtonClicked: (String, String) -> Unit = { _, _ -> },
    onRegisterButtonClicked: () -> Unit = {},
    errorEmailState: String? = null,
    errorPasswordState: String? = null
) {
    var email by remember { mutableStateOf("") }
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

    ) {
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
            Text(text = "Masuk", fontSize = 25.sp, color = Color.Black, modifier = Modifier.align(
                Alignment.CenterHorizontally).padding(all = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                isError = errorEmailState != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errorEmailState != null) {
                Text(
                    text = errorEmailState
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                isError = errorPasswordState != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errorPasswordState != null) {
                Text(
                    text = errorPasswordState
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onLoginButtonClicked(email, password) },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Masuk")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Belum punya akun? Daftar sekarang", fontSize = 15.sp, modifier = Modifier.align(
                Alignment.CenterHorizontally))

            Button(onClick = onRegisterButtonClicked, colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main)), modifier = Modifier.fillMaxWidth()) {
                Text(text = "Daftar")
            }
        }
    }
}