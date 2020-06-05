package br.com.exception.catnoot.repository

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact



class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    private val textViewContactName = view.findViewById<TextView>(R.id.textViewContactName)
    private val textViewContactPhone = view.findViewById<TextView>(R.id.textViewContactPhone)
    lateinit var database: ContactDatabase



    fun bind(contact: Contact) {
        textViewContactName.text =  contact.name
        textViewContactPhone.text = contact.phoneNumber
/*
        delete_button.setOnClickListener {
            val contactId = contact.id

            val result = database.removeContact(contactId)

            if (result == -1) {
                Toast.makeText(activity, "Erro ao excluir", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Excluído com sucesso", Toast.LENGTH_SHORT).show()
            }
        }
 */
    }


}