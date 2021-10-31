package com.example.homework15.ui

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.homework15.R
import com.example.homework15.base.BaseFragment
import com.example.homework15.databinding.FragmentProfileBinding
import com.example.homework15.datastore.DataStoreManager
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun init() {
        readFromDatastore()
        setListeners()
    }

    private fun setListeners() {
        binding.editBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }

    private fun readFromDatastore() {
        val datastoreManager = DataStoreManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            datastoreManager.readFromDataStore().observe(viewLifecycleOwner, { user ->
                binding.firstNameField.editText?.setText(user.firsName)
                Glide.with(requireContext())
                    .load(user.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imgView)
                binding.lastNameField.editText?.setText(user.lastName)
                binding.emailField.editText?.setText(user.email)
                binding.ageField.editText?.setText(user.age)
                binding.addressField.editText?.setText(user.address)
                binding.genderField.editText?.setText(user.gender)
                binding.phoneNumberField.editText?.setText(user.phoneNumber)
            })
        }

    }

}