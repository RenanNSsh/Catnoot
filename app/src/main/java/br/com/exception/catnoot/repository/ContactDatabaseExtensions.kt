package br.com.exception.catnoot.repository

import android.content.ContentValues
import br.com.exception.catnoot.model.Contact

val contactTableName = "Contact"
fun ContactDatabase.listContacts(): List<Contact> {

    val sql = """
        SELECT * FROM ${contactTableName}
    """.trimIndent()
    val db = this.readableDatabase
    val cursor = db.rawQuery(sql, null)

    val contacts = mutableListOf<Contact>()

    while (cursor.moveToNext()) {

        val contactId = cursor.getInt(cursor.getColumnIndex("id"))
        val contactName = cursor.getString(cursor.getColumnIndex("contactName"))
        val contactPhone = cursor.getString(cursor.getColumnIndex("contactPhoneNumber"))

        val contact = Contact(
                id = contactId,
                name = contactName,
                phoneNumber = contactPhone
        )

        contacts.add(contact)
    }

    cursor.close()

    return contacts
}

fun ContactDatabase.addContact(contact: Contact):Long {
    val db = this.writableDatabase

    val result = db.insert(contactTableName, null, ContentValues().apply {
        put("contactName", contact.name)
        put("ContactPhoneNumber", contact.phoneNumber)
    })

    return result
}

fun ContactDatabase.removeContact() {
}

fun ContactDatabase.updateContact() {
}
