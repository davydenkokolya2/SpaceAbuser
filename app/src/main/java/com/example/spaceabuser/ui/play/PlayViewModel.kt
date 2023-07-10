package com.example.spaceabuser.ui.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayViewModel @Inject constructor(): ViewModel() {
    private val _stateScore = MutableStateFlow<Int>(0)
    val stateScore: StateFlow<Int?> = _stateScore

    fun loadState(score: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateScore.emit(score)
        }
    }
}