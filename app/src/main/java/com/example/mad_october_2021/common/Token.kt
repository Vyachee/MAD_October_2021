package com.example.mad_october_2021.common

import java.io.Serializable

data class Token(
    val accessToken: String,
    val accessTokenExpiredAt: String,
    val refreshToken: String,
    val refreshTokenExpiredAt: String
): Serializable