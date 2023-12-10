package com.mssh.sooljari.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.components.TransparentIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    onNavigateToHome: () -> Unit,
    onResultCardClick: (Long) -> Unit,
    viewModel: AlcoholViewModel,
    initialQuery: String = "",
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var query by rememberSaveable { mutableStateOf("") }

    val isSearching = remember { mutableStateOf(false) }

    if (!initialQuery.isNullOrEmpty()) {
        query = initialQuery
        viewModel.initialLoad(initialQuery)
        focusManager.clearFocus()
    }

    val alcoholList by viewModel.alcoholList.collectAsState()

    Scaffold(
        topBar = {
            SearchAppBar(
                query = query,
                onTextChanged = {
                    query = it
                    isSearching.value = query.isNotEmpty()

                    //뷰모델에 사용자 입력 업데이트
                    viewModel.updateUserInput(it)
                },
                onTextReset = {
                    query = ""
                    isSearching.value = false
                    viewModel.resetSearchResult()
                },
                onNavigateToHome = onNavigateToHome,
                onSearchButtonClick = {
                    viewModel.initialLoad(query)
                    focusManager.clearFocus()
                    isSearching.value = false
                },
                onKeyboardSearch = {
                    viewModel.initialLoad(query)
                    focusManager.clearFocus()
                    isSearching.value = false
                },
                focusRequester = focusRequester
            )
        }
    ) { paddingValues ->
        //유저 검색 상태에 따라 다른 검색 화면 표시
        when {
            //검색어 입력 안했을 때
            query.isEmpty() && alcoholList.isNullOrEmpty() -> {
                SearchSuggestions(
                    viewModel = viewModel,
                    modifier = Modifier.padding(paddingValues),
                    onClickTag = {
                        query = it
                        viewModel.initialLoad(query)
                        focusManager.clearFocus()
                    }
                )
            }

            //검색중 일 때
            isSearching.value -> {
                SearchKeywords(
                    modifier = Modifier.padding(paddingValues),
                    viewModel = viewModel,
                    query = query,
                    onKeywordClick = {
                        //키워드 클릭 시 키워드로 검색
                        viewModel.initialLoad(it)
                        focusManager.clearFocus()
                        query = it
                        isSearching.value = false
                    }
                )
            }

            //위의 모든 상황이 해당되지 않으면 검색 결과 화면 표시
            else -> {
                SearchResults(
                    viewModel = viewModel,
                    query = query,
                    modifier = Modifier
                        .padding(paddingValues)
                        .background(colorResource(id = R.color.neutral1)),
                    results = alcoholList ?: emptyList(),
                    onResultCardClick = onResultCardClick
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun SearchAppBar(
    query: String,
    onTextChanged: (String) -> Unit,
    onTextReset: () -> Unit = {},
    onNavigateToHome: () -> Unit,
    onSearchButtonClick: () -> Unit,
    onKeyboardSearch: KeyboardActionScope.() -> Unit,
    focusRequester: FocusRequester
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp + statusBarHeight)
    ) {
        GlideImage(
            model = R.drawable.bg_main,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .padding(top = statusBarHeight)
                .matchParentSize()
        ) {
            //뒤로가기 버튼
            TransparentIconButton(
                onClick = onNavigateToHome,
                icon = painterResource(R.drawable.ic_arrow_left),
                iconColor = colorResource(R.color.neutral0),
                buttonSize = 32.dp,
                iconSize = 24.dp
            )

            SearchTextField(
                modifier = Modifier
                    .weight(1f),
                text = query,
                onTextChanged = onTextChanged,
                onTextReseted = onTextReset,
                onKeyboardSearch = onKeyboardSearch,
                focusRequester = focusRequester
            )

            //검색 버튼
            TransparentIconButton(
                onClick = onSearchButtonClick,
                icon = painterResource(R.drawable.ic_search),
                iconColor = colorResource(R.color.neutral0),
                buttonSize = 32.dp,
                iconSize = 24.dp
            )
        }
    }
}

@Composable
private fun SearchTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    onTextReseted: () -> Unit = {},
    modifier: Modifier = Modifier,
    onKeyboardSearch: KeyboardActionScope.() -> Unit,
    focusRequester: FocusRequester
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier
            .focusRequester(focusRequester),
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = onKeyboardSearch
        ),
        singleLine = true,
        cursorBrush = SolidColor(colorResource(id = R.color.black))
    ) { innerTextField ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxHeight()
                .background(
                    color = colorResource(id = R.color.neutral0_alpha65),
                    shape = RoundedCornerShape(3.dp)
                )
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.neutral0),
                    shape = RoundedCornerShape(3.dp)
                )
                .padding(horizontal = 8.dp)
        ) {
            innerTextField()
        }

        //리셋 버튼
        if (text.isNotEmpty()) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                TransparentIconButton(
                    modifier = Modifier.align(Alignment.End).padding(end = 3.dp),
                    onClick = onTextReseted,
                    icon = painterResource(R.drawable.ic_x),
                    iconColor = colorResource(R.color.neutral0),
                    buttonSize = 32.dp,
                    iconSize = 20.dp
                )
            }
        }
    }
}

@Preview(heightDp = 48)
@Composable
private fun SearchAppBarPreview() {
    val home: () -> Unit = {}
    SearchAppBar("", {}, {}, home, home, {}, FocusRequester())
}

@Preview(heightDp = 48)
@Composable
private fun SearchAppBarPreview2() {
    val home: () -> Unit = {}
    SearchAppBar("hello search", {}, {}, home, home, {}, FocusRequester())
}

/*
@Preview
@Composable
private fun SearchViewPreview() {
    val home: () -> Unit = {}
    SearchView(home)
}*/
