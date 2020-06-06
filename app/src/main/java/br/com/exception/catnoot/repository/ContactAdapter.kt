package br.com.exception.catnoot.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact


class ContactAdapter: RecyclerView.Adapter<ContactViewHolder>() {

    private val contacts = mutableListOf<Contact>()

    fun addContact(newContact: Contact){
        contacts.add(newContact)
        notifyDataSetChanged()
    }

    fun removeContact(contact: Contact){
        val contactId = contacts.indexOfFirst {
            contact.id == it.id
        }
        contacts.removeAt(contactId)
        notifyDataSetChanged()
    }

    fun updateContact(updatedContact: Contact): Boolean{
        val indexUpdateContact = contacts.indexOfFirst {
            updatedContact.id == it.id
        }
        if(indexUpdateContact != -1){
            contacts[indexUpdateContact] = updatedContact
            notifyDataSetChanged()
            return true
        }
        return false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

}