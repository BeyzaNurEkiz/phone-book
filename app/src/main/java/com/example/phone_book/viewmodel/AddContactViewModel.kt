package com.example.phone_book.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.phone_book.repo.ContactDaoRepository

class AddContactViewModel : ViewModel() {
    var crepo = ContactDaoRepository()

    fun add(contact_name: String, contact_number: String) {
        crepo.addContact(contact_name, contact_number)
    }
}