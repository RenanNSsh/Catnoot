package br.com.exception.catnoot.repository

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    private val textViewContactName = view.findViewById<TextView>(R.id.textViewContactName)
    private val textViewContactPhone = view.findViewById<TextView>(R.id.textViewContactPhone)


    fun bind(contact: Contact) {

        textViewContactName.text =  contact.name
        textViewContactPhone.text = contact.phoneNumber
    }
}