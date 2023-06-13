package com.selab.killer.client

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class ReactiveBotClient(val webClient: WebClient) : BotClient {
    companion object {
        private const val HEALTH_CHECK_END_POINT_URL = "/health"
    }

    override suspend fun health(): Map<String, Any> {
        return webClient.get()
            .uri(HEALTH_CHECK_END_POINT_URL)
            .retrieve()
            .awaitBody()
    }
}
