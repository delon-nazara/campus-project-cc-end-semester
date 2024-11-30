package com.example.proyekakhircloudcomputing.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhircloudcomputing.R

@Preview
@Composable
fun WelcomeScreen(
    onLoginButtonClicked: () -> Unit = {},
    onRegisterButtonClicked: () -> Unit = {},
    loadingState: Boolean = false
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.yellow_background))
                .fillMaxHeight()
                .padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.title),
                contentDescription = null,
                modifier = Modifier.size(250.dp).padding(all = 1.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.gambar),
                contentDescription = null,
                modifier = Modifier.size(400.dp).padding(1.dp)
            )
            Text(
                text = "Selamat Datang!",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp),
                color = colorResource(R.color.orange)

            )
            Button(
                enabled = !loadingState,
                colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_main)),
                onClick = onLoginButtonClicked,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 56.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(text = "Masuk", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                enabled = !loadingState,
                onClick = onRegisterButtonClicked,
                border = BorderStroke(2.dp, colorResource(R.color.blue_main)),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 56.dp),
                shape = RoundedCornerShape(25.dp),
            ) {
                Text(text = "Daftar", fontSize = 16.sp, color = colorResource(R.color.blue_main))
            }
        }
        if (loadingState) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).size(75.dp)
            )
        }
    }
}