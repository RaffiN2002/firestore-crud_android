package com.example.crudwithfirebase.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crudwithfirebase.util.ShareViewModel
import com.example.crudwithfirebase.util.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetDataScreen(
    navController: NavController,
    shareViewModel: ShareViewModel
) {
    var userID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var yob: String by remember { mutableStateOf("") }
    var yobInt: Int by remember { mutableStateOf(0) }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        )
        {
            IconButton(onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // userID
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    value = userID,
                    onValueChange = {
                        userID = it
                    },
                    label = {
                        Text(text = "UserID")
                    }
                )
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    onClick = {
                        shareViewModel.retrieveData(
                            userID = userID,
                            context = context
                        ) { data ->
                            name = data.name
                            yob = data.yob.toString()
                            yobInt = yob.toInt()
                        }
                    }
                ) {
                    Text(text = "Get Data")
                }
            }
            // Name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = "Name")
                }
            )
            // Age
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = yob,
                onValueChange = {
                    yob = it
                    if (yob.isNotEmpty()) {
                        yobInt = yob.toInt()
                    }
                },
                label = {
                    Text(text = "Year of Birth")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val User = User(
                        userID = userID,
                        name = name,
                        yob = yobInt
                    )

                    shareViewModel.saveData(User = User, context = context)
                }
            ) {
                Text(text = "Save")
            }
            // delete Button
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onClick = {
                    shareViewModel.deleteData(
                        userID = userID,
                        context = context,
                        navController = navController
                    )
                }
            ) {
                Text(text = "Delete")
            }
        }
    }
}