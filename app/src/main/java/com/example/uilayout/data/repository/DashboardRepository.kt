package com.example.uilayout.data.repository

import com.example.uilayout.data.model.LayoutResponse
import com.example.uilayout.data.network.ApiService

class DashboardRepository(private val apiService: ApiService) {
    suspend fun fetchDashboardLayout(): LayoutResponse {
        return apiService.getDashboardLayout()
    }
}
