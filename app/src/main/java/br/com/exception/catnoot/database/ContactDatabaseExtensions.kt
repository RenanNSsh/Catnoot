package br.com.exception.catnoot.database

import br.com.exception.catnoot.model.Contact


fun ContactDatabase.listContacts(): List<Contact> {

    val sql = """
        SELECT * FROM Contact
    """.trimIndent()
    val db = this.readableDatabase
    val cursor = db.rawQuery(sql, null)

    val contacts = mutableListOf<Contact>()

    while (cursor.moveToNext()) {

        val contactId = cursor.getInt(cursor.getColumnIndex("id"))
        val contactName = cursor.getString(cursor.getColumnIndex("contactName"))
        val contactPhone = cursor.getString(cursor.getColumnIndex("contactPhoneNumber"))

        val contact = Contact(
                contactId = contactId,
                contactName = contactName,
                contactPhoneNumber = contactPhone
        )

        contacts.add(contact)
    }

    cursor.close()

    return contacts
}

fun ContactDatabase.addContact() {
}

fun ContactDatabase.removeContact() {
}

fun ContactDatabase.updateContact() {
}
