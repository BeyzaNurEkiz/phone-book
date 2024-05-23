package com.example.phone_book.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phone_book.entity.Contacts
import com.example.phone_book.repo.ContactDaoRepository

class HomePageViewModel : ViewModel() {
    var crepo = ContactDaoRepository()
    var contactList = MutableLiveData<List<Contacts>>()

    init {
        uploadContacts()
        contactList = crepo.getAllContact()
    }

    fun uploadContacts() {
        crepo.allContacts()
    }

    fun search(search: String) {
        crepo.searchContact(search)
    }

    fun delete(contact_id: Int) {
        crepo.deleteContact(contact_id)
    }
}