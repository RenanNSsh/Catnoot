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
import br.com.exception.catnoot.repository.updateContact
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ContactAddFragment : Fragment() {
    lateinit var database: ContactDatabase
    lateinit var contactAdapter: ContactAdapter
    var contactUpdate: Contact? = null

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
        arguments?.getString("contact")?.let{
            contactUpdate =  Gson().fromJson(it, Contact::class.java)
        }

        contactAdapter = ContactAdapter()
        val textViewContactName = view.findViewById<EditText>(R.id.contactName)
        val textViewContactPhoneNumber = view.findViewById<EditText>(R.id.contactPhoneNumber)
        val textViewContactEmail = view.findViewById<EditText>(R.id.contactEmail)
        val addButton = view.findViewById<Button>(R.id.add_button)

        contactUpdate?.let{
            textViewContactName.setText(it.name)
            textViewContactEmail.setText(it.email)
            textViewContactPhoneNumber.setText(it.phoneNumber)

            addButton.text = "Atualizar"
        }

        addButton.setOnClickListener {

            val contact = Contact(
                textViewContactName.text.toString(),
                textViewContactPhoneNumber.text.toString(),
                textViewContactEmail.text.toString()
            )
            contactUpdate?.let{
                updateContact(contact.copy(id = it.id))
            } ?: run {
                createContact(contact)
            }
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun updateContact(contact: Contact){
        val contactId = database.updateContact(contact)

        if(contactId == -1){
            Toast.makeText(activity, "Erro ao atualizar", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity, "atualizado com sucesso", Toast.LENGTH_SHORT).show()
            contactAdapter.updateContact(contact)
        }
    }

    private fun createContact(contact: Contact){

        val contactId = database.addContact(contact)

        if(contactId == -1L){
            Toast.makeText(activity, "Erro ao inserir", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity, "Inserido com sucesso", Toast.LENGTH_SHORT).show()
            contactAdapter.addContact(contact.copy(id = contactId))
        }
    }

}
