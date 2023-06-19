package com.selab.killer.client

data class BotSignRequest(
    val email: String,
    val mbti: Long,
    val password: String
)

data class BotLoginRequest(
    val email: String,
    val password: String
)
