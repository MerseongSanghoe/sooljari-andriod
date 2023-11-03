package com.mssh.sooljari.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlcoholViewModel(private val repository: AlcoholRepository) : ViewModel() {
    //검색 결과 리스트
    private val _alcoholList = MutableStateFlow<List<Alcohol>?>(emptyList())
    val alcoholList: StateFlow<List<Alcohol>?> = _alcoholList.asStateFlow()

    //술 상세 정보
    private val _alcoholDetailModified = MutableStateFlow<AlcoholDetailModified?>(null)
    val alcoholDetailModified: StateFlow<AlcoholDetailModified?> = _alcoholDetailModified

    //페이지네이션 변수
    private var currentPage = 0
    private var totalAlcoholCount = 0
    private var _isLoading = MutableStateFlow(false)
    private val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun initialLoad(keyword: String) {
        viewModelScope.launch {
            val result = repository.getInitialResults(keyword)
            currentPage = result.page ?: 0
            totalAlcoholCount = result.count ?: 0
            _alcoholList.value = result.data
        }
    }

    fun loadMoreList(keyword: String) {
        if (isLoading.value) {
            Log.d("loadMoreList", "로딩중")
            return
        }

        _isLoading.value = true

        viewModelScope.launch {
            Log.d("loadMoreList", "로드 시작")
            if (canLoadMore()) {
                val nextPage = currentPage + 1
                val result = repository.getMoreResults(keyword, nextPage)

                currentPage = result.page ?: nextPage
                _alcoholList.value = _alcoholList.value?.plus(result.data)
                _isLoading.value = false
                Log.d("loadMoreList -> canLoadMore", "로드 함")
            }
        }
    }

    private fun canLoadMore(): Boolean {
        return _alcoholList.value?.size!! < totalAlcoholCount
    }

    fun login(id: String, pw: String) {
        viewModelScope.launch {
            repository.login(id, pw)
        }
    }

    fun getAlcoholDetailModified(id: Long) {
        viewModelScope.launch {
            _alcoholDetailModified.value = null
            _alcoholDetailModified.value = repository.getAlcoholDetailModified(id)
        }
    }
}

class AlcoholViewModelFactory(private val alcoholRepository: AlcoholRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AlcoholViewModel::class.java)) {
            AlcoholViewModel(alcoholRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}