package com.example.phone_book

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.phone_book.entity.Contacts
import com.example.phone_book.ui.theme.PhonebookTheme
import com.example.phone_book.viewmodel.ContactDetailViewModel
import com.example.phone_book.viewmodel.HomePageViewModel
import com.google.gson.Gson

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
                    PageTransitions()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageTransitions() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homepage") {
        composable("homepage") {
            HomePage(navController = navController)
        }
        composable("contact_add_page") {
            AddContact()
        }
        composable("contact_detail_page/{contact}", arguments = listOf(
            navArgument("contact") { type = NavType.StringType }
        )) {
            val json = it.arguments?.getString("contact")
            val obj = Gson().fromJson(json, Contacts::class.java)
            ContactDetail(obj)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val isSearching = remember {
        mutableStateOf(false)
    }
    val ct = remember {
        mutableStateOf("")
    }
    val viewModel: HomePageViewModel = viewModel()
    val contactList = viewModel.contactList.observeAsState(listOf())


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching.value) {
                        TextField(
                            value = ct.value,
                            onValueChange = {
                                ct.value = it
                                viewModel.search(it)
                            },
                            label = { Text(text = "Ara") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.White,
                                focusedLabelColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                                unfocusedLabelColor = Color.White,
                            )
                        )
                    } else {
                        Text(text = "Kişiler", color = Color.White)
                    }
                },
                actions = {
                    if (isSearching.value) {
                        IconButton(onClick = {
                            isSearching.value = false
                            ct.value = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.close_icon),
                                contentDescription = "",
                                tint = Color.White,
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            isSearching.value = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "",
                                tint = Color.White,
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mypink)
                )
            )
        },
        content = {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(70.dp))
                }
                items(
                    count = contactList.value!!.count(),
                    itemContent = {
                        val contact = contactList.value!![it]
                        Card(
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.clickable {
                                val contactJson = Gson().toJson(contact)
                                navController.navigate("contact_detail_page/${contactJson}")
                            }) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "${contact.contact_name} - ${contact.contact_number}")

                                    IconButton(onClick = {
                                        viewModel.delete(contact.contact_id)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_icon),
                                            contentDescription = "",
                                            tint = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            Button(
                onClick = { navController.navigate("contact_add_page") },
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

    }
}