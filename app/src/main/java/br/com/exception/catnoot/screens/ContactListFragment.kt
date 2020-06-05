package br.com.exception.catnoot.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.exception.catnoot.R
import br.com.exception.catnoot.model.Contact
import br.com.exception.catnoot.repository.ContactAdapter
import br.com.exception.catnoot.repository.ContactDatabase
import br.com.exception.catnoot.repository.listContacts
import kotlinx.android.synthetic.main.contact_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ContactListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var database: ContactDatabase
    lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView

        database = ContactDatabase(activity!!.applicationContext)

        contactAdapter = ContactAdapter()

        recyclerView.adapter = contactAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        database.listContacts().forEach{
            it.onRemove = ::removeContact
            contactAdapter.addContact(it)
        }


        fab.setOnClickListener{
            print("teste")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
    private fun removeContact(contact: Contact){
        contactAdapter.removeContact(contact)
    }
}
