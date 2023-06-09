package com.selab.killer.client

import com.selab.killer.extension.WebClientExtension
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfig {
    @Bean
    fun botClient(): BotClient {
        // TODO : baseUrl 필여함
        val webClient = WebClientExtension.generate(baseUrl = "htttp")
        return ReactiveBotClient(webClient)
    }
}
