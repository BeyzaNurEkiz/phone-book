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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.phone_book.R
import com.example.phone_book.viewmodel.AddContactViewModel
import com.example.phone_book.viewmodel.HomePageViewModel
import com.example.phone_book.viewmodelfactory.AddContactViewModelFactory
import com.example.phone_book.viewmodelfactory.HomePageViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContact(navController: NavController) {
    val ctName = remember { mutableStateOf("") }
    val ctNumber = remember { mutableStateOf("") }

    val localFocusManager =
        LocalFocusManager.current   // Geri tuşuna basılınca Texfieldlerdeki seçimi kaldırır.


    val context = LocalContext.current
    val viewModel: AddContactViewModel = viewModel(
        factory = AddContactViewModelFactory(context.applicationContext as Application)
    )

    val addSuccess = viewModel.addSuccess.observeAsState()

    LaunchedEffect(addSuccess.value) {
        if (addSuccess.value == true) {
            Toast.makeText(context, "Kayıt başarılı!", Toast.LENGTH_SHORT).show()
            navController.navigate("homepage") {
                popUpTo("homepage") { inclusive = true }
            }
            viewModel.clearAddSuccess()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kişi Kayıt", color = Color.White) },
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
                Button(
                    onClick = {
                        val contact_name = ctName.value
                        val contact_number = ctNumber.value
                        viewModel.add(
                            contact_name,
                            contact_number
                        )     //Butona basılınca viewmodeldeki add modeline bu bilgiler gidecek.
                        // o da repo içerisindeki addContact fonk götürecek.

                        localFocusManager.clearFocus()

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.mypink), // Arka plan rengi
                        contentColor = Color.White   // İçerik rengi (metin, ikon vs.)
                    ),
                    modifier = Modifier.size(250.dp, 50.dp)
                ) {
                    Text(text = "Kaydet")

                }
            }
        }
    )
}