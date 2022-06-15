package com.example.football_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.football_app.repositories.UserRepository
import com.example.football_app.utils.NetworkHelper

class ViewModelFactory(private val userRepository: UserRepository,
                       val networkHelper: NetworkHelper
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(userRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error")
    }
}