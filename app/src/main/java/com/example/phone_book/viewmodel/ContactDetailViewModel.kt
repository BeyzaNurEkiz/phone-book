package com.example.phone_book.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.phone_book.repo.ContactDaoRepository

class ContactDetailViewModel(application: Application) : AndroidViewModel(application) {
    var crepo = ContactDaoRepository(application)

    fun update(contact_id: Int, contact_name: String, contact_number: String) {
        crepo.updateContact(contact_id, contact_name, contact_number)
    }
}