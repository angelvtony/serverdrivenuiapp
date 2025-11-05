package com.example.uilayout.data.model

data class LayoutItem(
    val type: String,
    val text: String? = null,
    val title: String? = null,
    val value: String? = null,
    val url: String? = null,
    val options: List<String>? = null,
    val selected: Int? = null
)

