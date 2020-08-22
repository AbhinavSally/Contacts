package com.abhi.eg.contacts.Interface

import androidx.room.*
import com.abhi.eg.contacts.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact ORDER BY name ASC")
  suspend  fun getAllContacts(): List<Contact>

    @Insert
  suspend  fun addContact(contact : Contact)

    @Delete
  suspend  fun delete(contact: Contact)

    @Update
  suspend  fun updateContact(contact: Contact)
}