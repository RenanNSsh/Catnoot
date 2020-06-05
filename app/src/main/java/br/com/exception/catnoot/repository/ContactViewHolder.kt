package br.com.exception.catnoot.repository

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    private val textViewContactName = view.findViewById<TextView>(R.id.textViewContactName)
    private val textViewContactPhone = view.findViewById<TextView>(R.id.textViewContactPhone)
    private val buttonDeleteContact = view.findViewById<TextView>(R.id.delete_button)
    lateinit var database: ContactDatabase

    fun bind(contact: Contact) {

        textViewContactName.text =  contact.name
        textViewContactPhone.text = contact.phoneNumber

        val contactAdapter = ContactAdapter()
        database = ContactDatabase(itemView.context)
        buttonDeleteContact.setOnClickListener{

            val contactId = contact.id
            contactId?.let{
                val contactIdRemoved = database.removeContact(it)
                print(contactIdRemoved)
                if(contactIdRemoved == -1){
                    Toast.makeText(itemView.context, "Erro ao remover", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(itemView.context, "Removido com sucesso", Toast.LENGTH_SHORT).show()
                    contact.onRemove?.invoke(contact)
                }
            }

        }
    }


}