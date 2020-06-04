package br.com.exception.catnoot.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ContactDatabase(
    context: Context
): SQLiteOpenHelper(context, "contact.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createSql = """
            CREATE TABLE Contact(
                id INTEGER PRIMARY KEY autoincrement,
                contactName TEXT,
                contactPhoneNumber TEXT
            )
        """.trimIndent()

        val insertSql = """
            INSERT INTO Contact(
                id,
                contactName,
                contactPhoneNumber
            ) VALUES (1,'Alissito', '9999-9999')
        """.trimIndent()

        db?.execSQL(createSql)
        db?.execSQL(insertSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Contact;")
        onCreate(db)
    }

}