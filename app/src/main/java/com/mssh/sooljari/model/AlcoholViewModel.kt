package com.mssh.sooljari.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlcoholViewModel(private val repository: AlcoholRepository) : ViewModel() {
    private val _alcoholResults = MutableStateFlow<AlcoholResults?>(null)
    val alcoholResults: StateFlow<AlcoholResults?> = _alcoholResults

    private val _alcoholDetail = MutableStateFlow<AlcoholResponse?>(null)
    val alcoholDetail: StateFlow<AlcoholResponse?> = _alcoholDetail

    fun getAlcoholList(keyword: String) {
        viewModelScope.launch {
            _alcoholResults.value = repository.requestResults(keyword)
        }
    }

    fun getAlcoholDetail(id: Long) {
        viewModelScope.launch {
            _alcoholDetail.value = null
            _alcoholDetail.value = repository.getAlcoholDetail(id)
        }
    }

    fun login(id: String, pw: String) {
        viewModelScope.launch {
            repository.login(id, pw)
        }
    }
}

class AlcoholViewModelFactory(private val alcoholRepository: AlcoholRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AlcoholViewModel::class.java)) {
            AlcoholViewModel(alcoholRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}