package com.buvgm.ui.nuevoproducto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewProductViewModel: ViewModel() {
    private val _statusApp: MutableStateFlow<StatusApp> = MutableStateFlow<StatusApp>(StatusApp.default)
    val statusApp : StateFlow<StatusApp> = _statusApp

    sealed class  StatusApp{
        object default: StatusApp()
        object failure: StatusApp()
        object loading: StatusApp()
    }

    fun reset(){
        _statusApp.value = StatusApp.default
    }

    fun fallo(){
        _statusApp.value = StatusApp.failure
    }

    fun carga(){
        viewModelScope.launch {
            _statusApp.value = StatusApp.loading
            delay(1000L)
        }
    }
}