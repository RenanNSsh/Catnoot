package br.com.exception.catnoot.repository

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact
import com.google.gson.Gson

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    private val textViewContactName = view.findViewById<TextView>(R.id.textViewContactName)
    private val textViewContactPhone = view.findViewById<TextView>(R.id.textViewContactPhone)

    private val buttonDeleteContact = view.findViewById<Button>(R.id.delete_button)
    lateinit var database: ContactDatabase

    fun bind(contact: Contact) {

        itemView.setOnClickListener{ view ->
            val bundle = Bundle()
            bundle.putString("contact",Gson().toJson(contact.copy(onRemove = null)))
            view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

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