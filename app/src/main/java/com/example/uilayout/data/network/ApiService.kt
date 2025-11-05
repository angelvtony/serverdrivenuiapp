package com.example.uilayout.data.network

import com.example.uilayout.data.model.LayoutResponse
import retrofit2.http.GET

interface ApiService {
    @GET("layout/dashboard")
    suspend fun getDashboardLayout(): LayoutResponse
}
