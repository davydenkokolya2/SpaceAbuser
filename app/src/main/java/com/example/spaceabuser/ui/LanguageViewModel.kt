package com.example.spaceabuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor() : ViewModel() {
    private val _stateLanguage = MutableStateFlow<String>("ru")
    val stateLanguage: StateFlow<String> = _stateLanguage

    fun loadState(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateLanguage.emit(language)
        }
    }
}