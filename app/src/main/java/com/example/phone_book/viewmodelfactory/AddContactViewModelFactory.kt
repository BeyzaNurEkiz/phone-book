package com.example.phone_book.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_book.viewmodel.AddContactViewModel
import com.example.phone_book.viewmodel.HomePageViewModel

class AddContactViewModelFactory(var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddContactViewModel(application) as T
    }
}