package com.example.uilayout.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uilayout.data.model.LayoutItem
import com.example.uilayout.data.network.ApiService
import com.example.uilayout.data.repository.ApiClient
import com.example.uilayout.data.repository.ApiClient.apiService
import com.example.uilayout.data.repository.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _layoutData = MutableLiveData<List<LayoutItem>>()
    private val repository = DashboardRepository(ApiClient.apiService)
    val layoutData: LiveData<List<LayoutItem>> = _layoutData

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadDashboardLayout() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val response = repository.fetchDashboardLayout()
                _layoutData.postValue(response.layout)
            } catch (e: Exception) {
                e.printStackTrace()
                _layoutData.postValue(emptyList())
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}