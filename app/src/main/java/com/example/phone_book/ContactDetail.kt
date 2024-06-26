package com.example.phone_book

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.phone_book.entity.Contacts
import com.example.phone_book.viewmodel.AddContactViewModel
import com.example.phone_book.viewmodel.ContactDetailViewModel
import com.example.phone_book.viewmodel.HomePageViewModel
import com.example.phone_book.viewmodelfactory.ContactDetailViewModelFactory
import com.example.phone_book.viewmodelfactory.HomePageViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetail(incomingContact: Contacts, navController: NavController) {
    val ctName = remember { mutableStateOf("") }
    val ctNumber = remember { mutableStateOf("") }
    val localFocusManager =
        LocalFocusManager.current   // Geri tuşuna basılınca Texfieldlerdeki seçimi kaldırır.

    val context = LocalContext.current
    val viewModel: ContactDetailViewModel = viewModel(
        factory = ContactDetailViewModelFactory(context.applicationContext as Application)
    )
    LaunchedEffect(key1 = true) {
        ctName.value = incomingContact.contact_name
        ctNumber.value = incomingContact.contact_no
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kişi Detay", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mypink)
                )
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = ctName.value,
                    onValueChange = { ctName.value = it },
                    label = { Text(text = "Kişi Adı") })
                TextField(
                    value = ctNumber.value,
                    onValueChange = { ctNumber.value = it },
                    label = { Text(text = "Kişi Numarası") })
                Button(onClick = {
                    val contact_name = ctName.value
                    val contact_number = ctNumber.value
                    viewModel.update(incomingContact.contact_id, contact_name, contact_number)

                    localFocusManager.clearFocus()
                    Toast.makeText(context, "Güncelleme başarılı!", Toast.LENGTH_SHORT).show()
                    navController.navigate("homepage") {
                        popUpTo("homepage") { inclusive = true }
                    }
                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Güncelle")
                }
            }
        }
    )
}