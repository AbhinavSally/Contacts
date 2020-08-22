package com.abhi.eg.contacts

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Contact(
    val name: String,
  //  val profilePic:Int,
    val phoneNumber: String,
    val image: String

): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}