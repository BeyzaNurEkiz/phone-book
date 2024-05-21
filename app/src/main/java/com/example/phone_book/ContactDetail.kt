package com.example.phone_book

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.phone_book.entity.Contacts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetail(incomingContact : Contacts) {
    val ctName= remember { mutableStateOf("") }
    val ctNumber= remember { mutableStateOf("") }
    val localFocusManager= LocalFocusManager.current   // Geri tuşuna basılınca Texfieldlerdeki seçimi kaldırır.

    LaunchedEffect(key1 = true) {
        ctName.value= incomingContact.contact_name
        ctNumber.value= incomingContact.contact_number
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Kişi Detay", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mypink)
                )
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = ctName.value,
                    onValueChange = {ctName.value= it},
                    label = { Text(text = "Kişi Adı") })
                TextField(
                    value = ctNumber.value,
                    onValueChange = {ctNumber.value= it},
                    label = { Text(text = "Kişi Numarası") })
                Button(onClick = {
                    val contact_name=ctName.value
                    val contact_number=ctNumber.value
                    Log.e("Kişi Güncelle", "${incomingContact.contact_id} - $contact_name - $contact_number")

                    localFocusManager.clearFocus()

                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Güncelle")
                }
            }
        }
    )
}