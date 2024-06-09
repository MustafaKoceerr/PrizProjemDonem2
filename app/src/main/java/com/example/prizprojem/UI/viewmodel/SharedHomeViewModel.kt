package com.example.prizprojem.UI.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prizprojem.data.model.Rule
import com.example.prizprojem.data.repository.PrizRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class SharedHomeViewModel(
    private val repository: PrizRepository
) :ViewModel(){

    private var _rules = MutableLiveData<List<Rule>>()

    val rules : LiveData<List<Rule>>
        get() = _rules


    fun getRulesViewModel(){
        viewModelScope.launch {
            try {
                val response = repository.getRules()
                if (response.isNullOrEmpty()) {
                    Log.d("apiRequest", "Response successful but model is wrong, can't take data, null")
                } else {
                    Log.d("apiRequest", "Response successful and model taken")
                    _rules.postValue(response) // Use postValue to update LiveData

                }
            } catch (e: Exception) {
                Log.e("apiRequest", "Error fetching rules", e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        /*
           if (::job.isInitialized)
            job.cancel()
         */
    }

}