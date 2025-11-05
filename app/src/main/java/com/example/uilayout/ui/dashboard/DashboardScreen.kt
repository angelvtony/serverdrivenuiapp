package com.example.uilayout.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uilayout.ui.dashboard.component.RenderLayoutItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    val layoutItems by viewModel.layoutData.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = { viewModel.loadDashboardLayout() }
    )

    LaunchedEffect(Unit) {
        viewModel.loadDashboardLayout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        if (layoutItems.isEmpty() && !isLoading) {
            Text(
                text = "No layout data available",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(layoutItems) { item ->
                    RenderLayoutItem(item)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
