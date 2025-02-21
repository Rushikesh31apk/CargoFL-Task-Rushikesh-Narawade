package com.rushikesh31apk.task.ui_layer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rushikesh31apk.task.data.fromServer.ApiInstance
import com.rushikesh31apk.task.data.fromServer.responce.TaskResponceItem
import com.rushikesh31apk.task.ui_layer.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<TaskResponceItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<TaskResponceItem>>> = _uiState

    private var currentPage = 1
    private val pageSize = 10

    init {
        getImages()
    }

    fun getImages() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading  // Set loading state
            try {
                val response = ApiInstance.api.getImages(limit = pageSize, page = currentPage, order = "Desc")
                currentPage++

                // Append new data to the existing list
                val currentData = (_uiState.value as? UiState.Success)?.data ?: emptyList()
                _uiState.value = UiState.Success(currentData + response)

                Log.d("API_RESPONSE", response.toString())

            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred")
                Log.e("API_ERROR", e.message.toString())
            }
        }
    }
}
