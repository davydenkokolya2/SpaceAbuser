package com.example.spaceabuser.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(): ViewModel() {
    private val _stateSound = MutableStateFlow<String>("on")
    val stateSound: StateFlow<String?> = _stateSound

    fun loadStateSound(sound: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateSound.emit(sound)
        }
    }

    private val _stateMusic = MutableStateFlow<String>("on")
    val stateMusic: StateFlow<String?> = _stateMusic

    fun loadStateMusic(music: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMusic.emit(music)
        }
    }
}