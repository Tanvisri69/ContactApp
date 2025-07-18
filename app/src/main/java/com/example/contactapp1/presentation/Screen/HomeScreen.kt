package com.example.contactapp1.presentation.Screen

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contactapp1.presentation.ContactState
import com.example.contactapp1.presentation.ContactViewModel
import com.example.contactapp1.presentation.navigation.Routes
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    state: ContactState,
    viewModel: ContactViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contact Keeper") },
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Sort,
                        contentDescription = "Sort",
                        modifier = Modifier.clickable {
                            viewModel.changeIsSorting()
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Routes.AddEdit.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(state.contacts) { contact ->
                    val bitmap = contact.image?.let {
                        BitmapFactory.decodeByteArray(it, 0, it.size)
                    }?.asImageBitmap()

                    contactCard(
                        name = contact.name,
                        phone = contact.phone,
                        email = contact.email,
                        imageByteArray = contact.image,
                        image = bitmap,
                        dateOfCreation = contact.dateOfCreation,
                        id = contact.id,
                        viewModel = viewModel,
                        state = state,
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}

@Composable
fun contactCard(
    name: String,
    phone: String,
    email: String,
    imageByteArray: ByteArray?,
    image: ImageBitmap?,
    dateOfCreation: Long,
    id: Int,
    viewModel: ContactViewModel,
    state: ContactState,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    Card(
        onClick = {
            state.id.value = id
            state.name.value = name
            state.phone.value = phone
            state.email.value = email
            state.image.value = imageByteArray
            state.dateOfCreation.value = dateOfCreation
            navHostController.navigate(Routes.AddEdit.route)

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (image != null) {
                Image(
                    bitmap = image,
                    contentDescription = "Contact Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Contact Image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onPrimaryContainer)
                        .padding(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(
                    modifier = Modifier.width(4.dp)
                )
                Text(
                    text = phone,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(
                    modifier = Modifier.width(4.dp)
                )
                Text(
                    text = email,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

            }

            Spacer(modifier = Modifier.width(4.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = {
                        state.id.value = id
                        state.name.value = name
                        state.phone.value = phone
                        state.email.value = email
                        state.dateOfCreation.value = dateOfCreation
                        viewModel.deleteContact()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error
                    )
                }
                IconButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_CALL)
                        intent.data = "tel: $phone".toUri()
                        context.startActivity(intent)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = "Call", tint = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
