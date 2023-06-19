package com.selab.killer.client

interface BotClient {
    suspend fun health(): Map<String, Any>

    suspend fun sign(request: BotSignRequest): Map<String, Any>

    suspend fun login(request: BotLoginRequest): Map<String, Any>
}
