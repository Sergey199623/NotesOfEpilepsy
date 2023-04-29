package com.belyakov.auth.data

data class AuthModel(
    val login: String,
    val name: String = "",
    val loading: Boolean = false,
    val error: String = "",
)