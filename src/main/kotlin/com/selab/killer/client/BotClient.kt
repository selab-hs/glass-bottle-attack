package com.selab.killer.client

interface BotClient {
    suspend fun health(): Map<String, Any>
}
