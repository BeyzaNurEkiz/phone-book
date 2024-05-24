package com.example.phone_book.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="kisiler")
data class Contacts(@PrimaryKey (autoGenerate = true)
                    @ColumnInfo(name="kisi_id") @NotNull var contact_id:Int,
                    @ColumnInfo(name="kisi_ad") @NotNull var contact_name:String,
                    @ColumnInfo(name="kisi_tel") @NotNull var contact_no:String,)
{

}