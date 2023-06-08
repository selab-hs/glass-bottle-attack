package com.selab.killer.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfig {
    @Bean
    fun botClient(): BotClient {
        return ReactiveBotClient()
    }
}
