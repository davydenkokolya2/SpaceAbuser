package com.example.spaceabuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceabuser.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreensViewModel @Inject constructor(): ViewModel() {
    private val _stateScreen = MutableStateFlow<Screens?>(Screens.MAIN)
    val stateScreen: StateFlow<Screens?> = _stateScreen

    fun loadState(screens: Screens) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateScreen.emit(screens)
        }
    }
}