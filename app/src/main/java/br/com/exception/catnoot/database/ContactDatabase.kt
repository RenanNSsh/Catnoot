package br.com.exception.catnoot.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ContactDatabase(
    context: Context
): SQLiteOpenHelper(context, "contact.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            CREATE TABLE Contacts(
                id INTEGER PRIMARY KEY autoincrement,
                contactName TEXT,
                contactPhoneNumber TEXT
            )
        """.trimIndent()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Contact;")
        onCreate(db)
    }

}