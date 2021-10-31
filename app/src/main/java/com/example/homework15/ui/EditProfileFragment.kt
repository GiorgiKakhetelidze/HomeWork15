package com.example.homework15.ui

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework15.R
import com.example.homework15.base.BaseFragment
import com.example.homework15.databinding.FragmentEditProfileBinding
import com.example.homework15.datastore.DataStoreManager
import com.example.homework15.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EditProfileFragment :
    BaseFragment<FragmentEditProfileBinding>(FragmentEditProfileBinding::inflate) {

    override fun init() {
        setListeners()
    }

    private fun setListeners() {
        val dataStoreManager = DataStoreManager(requireContext())
        binding.saveBtn.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                dataStoreManager.saveToDataStore(getUser())
            }
            goToProfile()
        }
    }

    private fun goToProfile(){
        findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
    }

    private fun getUser(): User {
        val firstName = binding.firstNameField.editText?.text.toString()
        val lastName = binding.lastNameField.editText?.text.toString()
        val age = binding.ageField.editText?.text.toString()
        val email = binding.emailField.editText?.text.toString()
        val gender = binding.genderField.editText?.text.toString()
        val address = binding.addressField.editText?.text.toString()
        val number = binding.phoneNumberField.editText?.text.toString()
        val image = ""
        return User(
            firsName = firstName,
            lastName = lastName,
            age = age,
            email = email,
            gender = gender,
            address = address,
            phoneNumber = number,
            image = image
        )
    }
}