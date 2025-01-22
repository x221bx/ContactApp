package com.example.contactappa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappa.databinding.ItemContactBinding
class ContactAdapter(private val onDelete: (Contact) -> Unit) : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position), onDelete)
    }

    class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact, onDelete: (Contact) -> Unit) {
            binding.personName.text = contact.name
            binding.emailText.text = contact.email
            binding.phoneText.text = contact.phone
            binding.deleteIcon.setOnClickListener {
                onDelete(contact)
            }
        }
    }

    class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.email == newItem.email // Assuming email is unique
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}
