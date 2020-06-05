package br.com.exception.catnoot.repository

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact
import com.google.gson.Gson

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    private val textViewContactName = view.findViewById<TextView>(R.id.textViewContactName)
    private val textViewContactPhone = view.findViewById<TextView>(R.id.textViewContactPhone)


    fun bind(contact: Contact) {

        itemView.setOnClickListener{ view ->
            val bundle = Bundle()
            bundle.putString("contact",Gson().toJson(contact))
            view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        textViewContactName.text =  contact.name
        textViewContactPhone.text = contact.phoneNumber
    }

}