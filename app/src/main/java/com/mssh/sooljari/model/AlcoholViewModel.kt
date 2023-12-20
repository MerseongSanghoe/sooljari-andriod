package com.mssh.sooljari.model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.json.Json.Default.encodeToString
import java.io.IOException

class AlcoholViewModel(private val repository: AlcoholRepository, private val application: Application) : AndroidViewModel(application) {
    //검색 결과 리스트
    private val _alcoholList = MutableStateFlow<List<Alcohol>?>(emptyList())
    val alcoholList: StateFlow<List<Alcohol>?> = _alcoholList.asStateFlow()

    //술 상세 정보
    private val _alcoholInfo = MutableStateFlow<AlcoholInfo?>(null)
    val alcoholInfo: StateFlow<AlcoholInfo?> = _alcoholInfo

    //태그로 가져온 술 리스트
    private val _alcoholListByTag =
        MutableStateFlow<MutableMap<String, SearchedByTagAlcoholResults?>>(mutableMapOf())
    val alcoholListByTag: StateFlow<MutableMap<String, SearchedByTagAlcoholResults?>> =
        _alcoholListByTag

    //자동완성 키워드 리스트
    private val _keywordList = MutableStateFlow<List<String>?>(emptyList())
    val keywordList: StateFlow<List<String>?> = _keywordList

    //페이지네이션 변수
    private var currentPage = 0
    private var totalAlcoholCount = 0
    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    val canLoadMore: Boolean
        get() = canLoadMore()

    //검색화면 사용자 인풋 플로우
    private val userInputFlow = MutableStateFlow("")
    
    // 검색기록
    private val _searchHistoryList = MutableStateFlow<List<String>>(emptyList())
    val searchHistoryList: StateFlow<List<String>> = _searchHistoryList

    private var reqUrl = ""

    /*
     검색 관련 함수들

     initialLoad

     loadMoreList

     canLoadMore

     resetSearchResult

     */

    fun initialLoad(keyword: String) {
        if (isLoading.value) return
        if (keyword.isBlank()) {
            resetSearchResult()
            return
        }

        val searchWord = keyword.trimIndent()

        _isLoading.value = true

        saveToHistory(searchWord)

        viewModelScope.launch {
            reqUrl = repository.getInitialResults(searchWord).reqUrl

            val result = repository.getInitialResults(searchWord).result
            currentPage = result.page ?: 0
            totalAlcoholCount = result.count ?: 0
            _alcoholList.value = result.data
            _isLoading.value = false
        }
    }

    private fun saveToHistory(keyword: String) {
        val context = application.applicationContext
        viewModelScope.launch {
            // 1. 기존 히스토리 맨 앞에 추가
            val newList: MutableList<String> = mutableListOf(keyword)
            newList.addAll(_searchHistoryList.value.filter { it != keyword })
            _searchHistoryList.emit(newList)
            // 2. json으로 변환
            val jsonString = encodeToString(ListSerializer(String.serializer()), newList.toList())
            // 3. 실제 데이터 저장
            context.openFileOutput("history.json", Context.MODE_PRIVATE).use { stream ->
                stream.write(jsonString.toByteArray())
            }
        }
    }

    private fun loadHistory() {
        val context = application.applicationContext
        viewModelScope.launch {
            // 1. 실제 데이터 가져오기
            var jsonString = "[]"
            try {
                context.openFileInput("history.json").bufferedReader().useLines { lines ->
                    jsonString = lines.joinToString("")
                }
            } catch (e: IOException) {
                Log.d("history", "No history.json file detected")
            }
            // 2. json to object
            val loadedList = decodeFromString<MutableList<String>>(jsonString)
            // 3. 검증 후 mutablestateflow emit
            if (loadedList.isNotEmpty())
                _searchHistoryList.emit((loadedList))
        }
    }

    fun loadMoreList() {
        if (isLoading.value) return

        _isLoading.value = true

        viewModelScope.launch {
            if (canLoadMore()) {
                val nextPage = currentPage + 1
                val result = repository.getMoreResults(reqUrl, nextPage)

                currentPage = result.page ?: nextPage
                _alcoholList.value = _alcoholList.value?.plus(result.data)
                _isLoading.value = false
            }
        }
    }

    private fun canLoadMore(): Boolean {
        return _alcoholList.value?.size!! < totalAlcoholCount
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

        loadHistory()
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
        viewModelScope.launch {
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

class AlcoholViewModelFactory(private val alcoholRepository: AlcoholRepository, private val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AlcoholViewModel::class.java)) {
            AlcoholViewModel(alcoholRepository, application) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}