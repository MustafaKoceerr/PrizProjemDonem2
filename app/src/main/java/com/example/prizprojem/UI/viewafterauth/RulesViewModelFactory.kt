package com.example.prizprojem.UI.viewafterauth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prizprojem.UI.viewmodel.SharedHomeViewModel
import com.example.prizprojem.data.repository.PrizRepository

class RulesViewModelFactory(
    private val repository: PrizRepository
) :ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedHomeViewModel(repository) as T
    }

}
