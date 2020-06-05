package br.com.exception.catnoot.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact
import br.com.exception.catnoot.repository.ContactAdapter
import br.com.exception.catnoot.repository.ContactDatabase
import br.com.exception.catnoot.repository.addContact

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ContactAddFragment : Fragment() {
    lateinit var database: ContactDatabase

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = ContactDatabase(activity!!.applicationContext)

        val contactAdapter = ContactAdapter()
        val textViewContactName = view.findViewById<EditText>(R.id.contactName)
        val textViewContactPhoneNumber = view.findViewById<EditText>(R.id.contactPhoneNumber)
        val textViewContactEmail = view.findViewById<EditText>(R.id.contactEmail)

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            val contact = Contact(
                    textViewContactName.text.toString(),
                    textViewContactPhoneNumber.text.toString(),
                    textViewContactEmail.text.toString()
            )

            val contactId = database.addContact(contact)

            if(contactId == -1L){
                Toast.makeText(activity, "Erro ao inserir", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "Inserido com sucesso", Toast.LENGTH_SHORT).show()
                contactAdapter.addContact(contact.copy(id = contactId))
            }
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
