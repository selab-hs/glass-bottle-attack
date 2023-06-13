package com.selab.killer.batch

import com.selab.killer.client.BotClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BotJob(
    private val botClient: BotClient
) {
    private val logger = mu.KotlinLogging.logger {}

    fun runAttackHealthCheckJob() {
        logger.info { "start Attack Health : ${LocalDateTime.now()}" }
        runBlocking {
            withContext(Dispatchers.IO) {
                launch {
                    (0..300).forEach { _ -> botClient.health() }
                }
                launch {
                    (0..300).forEach { _ -> botClient.health() }
                }
                launch {
                    (0..300).forEach { _ -> botClient.health() }
                }
            }
        }
    }
}
