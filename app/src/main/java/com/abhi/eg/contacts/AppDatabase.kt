package com.abhi.eg.contacts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abhi.eg.contacts.Interface.ContactDao


@Database(entities = [Contact::class],version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getContactDao() : ContactDao

    companion object{
       @Volatile private var instance : AppDatabase?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "contactdatabase"
        ).build()
    }
}