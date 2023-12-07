package com.mssh.sooljari.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class AlcoholViewModel(private val repository: AlcoholRepository) : ViewModel() {
    //검색 결과 리스트
    private val _alcoholList = MutableStateFlow<List<Alcohol>?>(emptyList())
    val alcoholList: StateFlow<List<Alcohol>?> = _alcoholList.asStateFlow()

    //술 상세 정보
    private val _alcoholInfo = MutableStateFlow<AlcoholInfo?>(null)
    val alcoholInfo: StateFlow<AlcoholInfo?> = _alcoholInfo

    //태그로 가져온 술 리스트
    private val _alcoholListByTag = MutableStateFlow<MutableMap<String, SearchedByTagAlcoholResults?>>(
        mutableMapOf()
    )
    val alcoholListByTag: StateFlow<MutableMap<String, SearchedByTagAlcoholResults?>> = _alcoholListByTag

    //자동완성 키워드 리스트
    private val _keywordList = MutableStateFlow<List<String>?>(emptyList())
    val keywordList: StateFlow<List<String>?> = _keywordList

    //페이지네이션 변수
    private var currentPage = 0
    private var totalAlcoholCount = 0
    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    //검색화면 사용자 인풋 플로우
    private val userInputFlow = MutableStateFlow("")

    /*
     검색 관련 함수들

     initialLoad

     loadMoreList

     canLoadMore

     resetSearchResult

     */

    fun initialLoad(keyword: String) {
        if (isLoading.value) return

        _isLoading.value = true

        viewModelScope.launch {
            val result = repository.getInitialResults(keyword)
            currentPage = result.page ?: 0
            totalAlcoholCount = result.count ?: 0
            _alcoholList.value = result.data
            _isLoading.value = false
        }
    }

    fun loadMoreList(keyword: String) {
        if (isLoading.value) return

        _isLoading.value = true

        viewModelScope.launch {
            if (canLoadMore()) {
                val nextPage = currentPage + 1
                val result = repository.getMoreResults(keyword, nextPage)

                currentPage = result.page ?: nextPage
                _alcoholList.value = _alcoholList.value?.plus(result.data)
                _isLoading.value = false
            }
        }
    }

    private fun canLoadMore(): Boolean {
        return (_alcoholList.value?.size!! - 1) < totalAlcoholCount
    }

    fun resetSearchResult() {
        currentPage = 0
        totalAlcoholCount = 0
        _alcoholList.value = emptyList()
    }

    /*
    로그인 관련 함수들

    login

     */

    fun login(id: String, pw: String) {
        viewModelScope.launch {
            repository.login(id, pw)
        }
    }

    /*
    술 상세 정보 관련 함수들

    getAlcoholInfo

     */

    fun getAlcoholInfo(id: Long) {
        viewModelScope.launch {
            _alcoholInfo.value = null
            _alcoholInfo.value = repository.getAlcoholInfo(id)
        }
    }

    /*
    태그 관련 함수들
     */

    fun getAlcoholsByTags(tags: List<String>) {
        viewModelScope.async {
            val newList = mutableMapOf<String, SearchedByTagAlcoholResults?>()
            for (tag in tags) {
                newList[tag] = null
                newList[tag] = repository.getAlcoholsByTag(tag)
            }
            _alcoholListByTag.emit(newList);
        }
    }

    /*
    자동 완성 관련 함수들

    getAutocompleteKeywords

    updateUserInput
     */

    @OptIn(FlowPreview::class)
    fun getAutocompleteKeywords() {
        viewModelScope.launch {
            userInputFlow
                .debounce(300)
                .filter { it.isNotBlank() }
                .collect { input ->
                    val autoCompletedKeywords = repository.getAutoCompleteKeywords(input).data
                    _keywordList.value = autoCompletedKeywords
                }
        }
    }

    fun updateUserInput(input: String) {
        userInputFlow.value = input
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