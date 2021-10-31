package com.example.homework15.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.asLiveData
import com.example.homework15.extensions.dataStore
import com.example.homework15.model.User
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    suspend fun saveToDataStore(user: User) {
        context.dataStore.edit { userInfo ->
            userInfo[FIRST_NAME_KEY] = user.firsName
            userInfo[LAST_NAME_KEY] = user.lastName
            userInfo[EMAIL_KEY] = user.email
            userInfo[AGE_KEY] = user.age
            userInfo[PHONE_NUMBER_KEY] = user.phoneNumber
            userInfo[GENDER_KEY] = user.gender
            userInfo[ADDRESS_KEY] = user.address
            userInfo[IMAGE_KEY] = user.image
        }
    }

    fun readFromDataStore() = context.dataStore.data.map { user ->
        User(
            firsName = user[FIRST_NAME_KEY] ?: "DefaultFirstName",
            lastName = user[LAST_NAME_KEY] ?: "DefaultLastName",
            email = user[EMAIL_KEY] ?: "DefaultEmail",
            phoneNumber = user[PHONE_NUMBER_KEY] ?: "DefaultPhoneNumber",
            gender = user[GENDER_KEY] ?: "DefaultGender",
            address = user[ADDRESS_KEY] ?: "DefaultAddress",
            age = user[AGE_KEY] ?: "DefaultAge",
            image = user[IMAGE_KEY] ?: "DefaultImg"
        )
    }.asLiveData()

    companion object {
        val FIRST_NAME_KEY = stringPreferencesKey("firsName")
        val LAST_NAME_KEY = stringPreferencesKey("lastName")
        val IMAGE_KEY = stringPreferencesKey("Image")
        val AGE_KEY = stringPreferencesKey("age")
        val EMAIL_KEY = stringPreferencesKey("email")
        val GENDER_KEY = stringPreferencesKey("gender")
        val ADDRESS_KEY = stringPreferencesKey("address")
        val PHONE_NUMBER_KEY = stringPreferencesKey("number")
    }

}