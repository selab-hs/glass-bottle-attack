package com.selab.killer.client

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class ReactiveBotClient(val webClient: WebClient) : BotClient {
    companion object {
        private const val HEALTH_CHECK_END_POINT_URL = "/health"
        private const val MEMBER_SIGN_URL = "/api/v1/members/signup"
        private const val AUTH_LOGIN_URL = "/api/v1/auth/login"
    }

    override suspend fun health(): Map<String, Any> {
        return webClient.get()
            .uri(HEALTH_CHECK_END_POINT_URL)
            .retrieve()
            .awaitBody()
    }

    override suspend fun sign(request: BotSignRequest): Map<String, Any> {
        return webClient.post()
            .uri(MEMBER_SIGN_URL)
            .bodyValue(request)
            .retrieve()
            .awaitBody()
    }

    override suspend fun login(request: BotLoginRequest): Map<String, Any> {
        return webClient.post()
            .uri(AUTH_LOGIN_URL)
            .bodyValue(request)
            .retrieve()
            .awaitBody()
    }
}
