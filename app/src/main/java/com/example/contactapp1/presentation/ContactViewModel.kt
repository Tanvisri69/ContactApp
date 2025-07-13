package com.example.contactapp1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp1.data.database.Contact
import com.example.contactapp1.data.database.ContactDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(var dataBase: ContactDataBase) : ViewModel() {
    private var isSortedByName = MutableStateFlow(true)
    private var contact = isSortedByName.flatMapLatest {
        if(it){
            dataBase.getDao().getContactsSortedByName()
        }
        else{
            dataBase.getDao().getContactsSortedByDate()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(ContactState())
    val state = combine(_state, contact, isSortedByName){state, contacts, isSortedByName ->
        state.copy(contacts = contacts)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun savedContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            phone = state.value.phone.value,
            email = state.value.email.value,
            isActive = true,
            dateOfCreation = System.currentTimeMillis().toLong(),
            image = state.value.image.value
        )

        viewModelScope.launch{
            dataBase.getDao().upsertContact(contact)
        }
        state.value.id.value = 0
        state.value.name.value = ""
        state.value.phone.value = ""
        state.value.email.value = ""
        state.value.image.value = null
    }

    fun changeIsSorting(){
        isSortedByName.value = !isSortedByName.value
    }

    fun deleteContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            phone = state.value.phone.value,
            email = state.value.email.value,
            isActive = true,
            dateOfCreation = System.currentTimeMillis().toLong(),
            image = state.value.image.value
        )

        viewModelScope.launch{
            dataBase.getDao().deleteContact(contact)
        }

        state.value.id.value = 0
        state.value.name.value = ""
        state.value.phone.value = ""
        state.value.email.value = ""
        state.value.dateOfCreation.value = 0
        state.value.image.value = null
    }
}