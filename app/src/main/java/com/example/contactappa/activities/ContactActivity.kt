package com.example.contactappa.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactappa.dialogs.AddContactDialog
import com.example.contactappa.models.Contact
import com.example.contactappa.adapters.ContactAdapter
import com.example.contactappa.viewmodels.ContactViewModel
import com.example.contactappa.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var viewModel: ContactViewModel
    private val adapter = ContactAdapter { contact ->
        viewModel.removeContact(contact)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        binding.reclView.layoutManager = GridLayoutManager(this, 2) // Set GridLayoutManager with 2 columns
        binding.reclView.adapter = adapter

        binding.fab.setOnClickListener {
            val dialog = AddContactDialog { name, email, phone ->
                viewModel.addContact(Contact(name, email, phone))
            }
            dialog.show(supportFragmentManager, "AddContactDialog")
        }

        viewModel.contacts.observe(this) { contacts ->
            if (contacts.isEmpty()) {
                binding.notFound.visibility = View.VISIBLE
                binding.reclView.visibility = View.GONE
            } else {
                binding.notFound.visibility = View.GONE
                binding.reclView.visibility = View.VISIBLE
                adapter.submitList(contacts)
            }
        }
    }
}
