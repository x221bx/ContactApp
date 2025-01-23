package com.example.contactappa.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactappa.databinding.DialogAddContactBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddContactDialog(private val onAdd: (String, String, String) -> Unit) : BottomSheetDialogFragment() {

    private var _binding: DialogAddContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enterUser.setOnClickListener {
            val name = binding.nameField.editText?.text.toString()
            val email = binding.emailField.editText?.text.toString()
            val phone = binding.phoneField.editText?.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                if (isValidEmail(email) && isValidPhone(phone)) {
                    onAdd(name, email, phone)
                    dismiss()
                } else {
                    // Show error messages if validation fails
                    if (!isValidEmail(email)) {
                        binding.emailField.error = "البريد الإلكتروني غير صحيح"
                    }
                    if (!isValidPhone(phone)) {
                        binding.phoneField.error = "رقم الهاتف غير صحيح"
                    }
                }
            } else {
                // Show error if any field is empty
                if (name.isEmpty()) {
                    binding.nameField.error = "اسم المستخدم مطلوب"
                }
                if (email.isEmpty()) {
                    binding.emailField.error = "البريد الإلكتروني مطلوب"
                }
                if (phone.isEmpty()) {
                    binding.phoneField.error = "رقم الهاتف مطلوب"
                }
            }
        }
    }

    // Function to validate email format
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        return email.matches(emailPattern.toRegex())
    }

    // Function to validate phone number format
    private fun isValidPhone(phone: String): Boolean {
        // Example: Validate phone number with 10 digits (adjust as needed)
        val phonePattern = "^[0-9]{10}$"
        return phone.matches(phonePattern.toRegex())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
