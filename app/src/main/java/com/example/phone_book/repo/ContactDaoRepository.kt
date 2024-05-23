package com.example.phone_book.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.phone_book.entity.Contacts

class ContactDaoRepository {
    var contactList =
        MutableLiveData<List<Contacts>>()      //Contact sınıfındaki nesneleri getirecek-listeleyecek.

    init {
        contactList =
            MutableLiveData()                               //contact listesini oluşturuyoruz.
    }

    fun getAllContact(): MutableLiveData<List<Contacts>> {
        return contactList
    }

    //sayfalardaki işlemleri artık tanımlayabiliriz.

    fun allContacts() {
        val list = mutableListOf<Contacts>()
        val k1 = Contacts(1, "Ahmet", "11111")
        val k2 = Contacts(2, "Beyza", "2222")
        list.add(k1)
        list.add(k2)

        contactList.value = list
    }

    fun searchContact(search: String) {
        Log.e("Kişi Arama", search)
    }

    fun addContact(contact_name: String, contact_number: String) {
        Log.e("Kişi Kayıt", "$contact_name - $contact_number")
    }

    fun updateContact(contact_id: Int, contact_name: String, contact_number: String) {
        Log.e("Kişi Güncelle", "$contact_id - $contact_name - $contact_number")
    }

    fun deleteContact(contact_id: Int) {
        Log.e("Kişi Sil", "$contact_id")
    }
}