package com.example.mad_october_2021.common

data class LastMessage(
    val id: String,
    val text: String,
    val createdAt: String,
    val user: User,
    val attachments: List<String>
)
