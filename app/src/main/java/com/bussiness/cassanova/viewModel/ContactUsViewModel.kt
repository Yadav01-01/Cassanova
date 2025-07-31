package com.bussiness.cassanova.viewModel


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ContactUsViewModel @Inject constructor() : ViewModel() {

    // Contact Us Form Fields
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _subject = MutableStateFlow("")
    val subject: StateFlow<String> = _subject

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    // Update functions
    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updatePhone(newPhone: String) {
        _phone.value = newPhone
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updateSubject(newSubject: String) {
        _subject.value = newSubject
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    // Submit function
    fun submitContactForm() {
        // Here you would typically handle form submission logic
        // For example, validating fields and making an API call
    }
}