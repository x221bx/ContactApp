package com.example.contactappa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    private val _contacts = MutableLiveData<List<Contact>>(emptyList())
    val contacts: LiveData<List<Contact>> = _contacts

    fun addContact(contact: Contact) {
        _contacts.value = _contacts.value?.toMutableList()?.apply { add(contact) }
    }
    fun removeContact(contact: Contact) {
        _contacts.value = _contacts.value?.toMutableList()?.apply { remove(contact) }
    }
}
