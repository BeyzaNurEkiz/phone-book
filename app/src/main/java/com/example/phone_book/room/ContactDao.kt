package com.example.phone_book.room

import androidx.room.*
import com.example.phone_book.entity.Contacts

@Dao
interface ContactDao {
    @Query("SELECT * FROM kisiler")
    suspend fun allContacts():List<Contacts>

    @Insert
    suspend fun addContact(kisiler: Contacts)

    @Update
    suspend fun updateContact(kisiler: Contacts)

    @Delete
    suspend fun deleteContact(kisiler: Contacts)

    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' || :searchWord || '%'")
    suspend fun searchContact(searchWord:String):List<Contacts>

}