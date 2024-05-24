package com.example.phone_book.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phone_book.entity.Contacts

@Database(entities = [Contacts::class],version = 1)
abstract class contactDatabase : RoomDatabase() {
    abstract fun contactDao():ContactDao

    companion object{
        var INSTANCE:contactDatabase? = null

        fun databaseAccess(context: Context):contactDatabase?{
            if (INSTANCE == null){

                synchronized(contactDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        contactDatabase::class.java,
                        "rehber.sqlite").createFromAsset("rehber.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}


