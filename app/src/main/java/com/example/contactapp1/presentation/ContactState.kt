package com.example.contactapp1.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contactapp1.data.database.Contact

data class ContactState (
    val contacts : List<Contact> = emptyList(),
    val id : MutableState<Int> = mutableStateOf(1),
    val name : MutableState<String> = mutableStateOf(""),
    val phone : MutableState<String> = mutableStateOf(""),
    val email : MutableState<String> = mutableStateOf(""),
    val dateOfCreation : MutableState<Long> = mutableStateOf(0),
    val image : MutableState<ByteArray?> = mutableStateOf(null),

    )