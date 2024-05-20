package com.example.phone_book

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.phone_book.ui.theme.PhonebookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhonebookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text(text = "Kişiler", color = Color.White)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mypink)
                )
            )
        },
        content = {},
        floatingActionButton = {
            Button(onClick = { Log.e("Button","Eklendi") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.mypink),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "",
                    tint = Color.White
                )
                Text(text = "Ekle")
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhonebookTheme {
        HomePage()
    }
}