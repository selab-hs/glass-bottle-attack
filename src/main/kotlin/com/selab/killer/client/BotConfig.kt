package com.selab.killer.client

import com.selab.killer.extension.WebClientExtension
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotBlank

@Configuration
class BotConfig(
    private val botProperties: BotProperties
) {
    @Bean
    fun botClient(): BotClient {
        val webClient = WebClientExtension.generate(baseUrl = botProperties.url)
        return ReactiveBotClient(webClient)
    }
}

@Configuration
@ConfigurationProperties(prefix = "bot.glass-bottle")
@ConfigurationPropertiesBinding
data class BotProperties(
    @field:NotBlank
    var url: String = "",
)
