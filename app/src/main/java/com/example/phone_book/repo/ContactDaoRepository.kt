package com.example.phone_book.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.phone_book.entity.Contacts
import com.example.phone_book.room.contactDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ContactDaoRepository(var application: Application) {
    var contactList =
        MutableLiveData<List<Contacts>>()      //Contact sınıfındaki nesneleri getirecek-listeleyecek.
    var db: contactDatabase
    var addSuccess = MutableLiveData<Boolean>()


    init {
        db = contactDatabase.databaseAccess(application)!!
        contactList =
            MutableLiveData()                               //contact listesini oluşturuyoruz.
    }

    fun getAllContact(): MutableLiveData<List<Contacts>> {
        return contactList
    }

    //sayfalardaki işlemleri artık tanımlayabiliriz.

    fun allContacts() {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            contactList.value = db.contactDao().allContacts()
        }
    }

    fun searchContact(search: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            contactList.value = db.contactDao().searchContact(search)
        }
    }

    fun addContact(contact_name: String, contact_number: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val newContact = Contacts(0, contact_name, contact_number)
            db.contactDao().addContact(newContact)
            addSuccess.value = true
        }
    }

    fun updateContact(contact_id: Int, contact_name: String, contact_number: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val updatedContact = Contacts(contact_id, contact_name, contact_number)
            db.contactDao().updateContact(updatedContact)
        }
    }

    fun deleteContact(contact_id: Int) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val deletedContact = Contacts(contact_id, "", "")
            db.contactDao().deleteContact(deletedContact)
            allContacts()
        }
    }
}