package com.selab.killer.application

import com.selab.killer.client.BotClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * 코루틴 기반
 */
@Service
class BotV1Service(
    private val botClient: BotClient
) {
    private val logger = mu.KotlinLogging.logger { }

    @Async(value = "taskExecutor")
    fun executeKiller() {
        runBlocking { killServer() }
        logger.info { "killer start / no stop...." }
    }

    suspend fun killServer() {
        withContext(Dispatchers.IO) {
            (0..9).forEach {
                launch { coHealthCall(it) }
            }
        }
    }

    private suspend fun coHealthCall(number: Int) {
        var count = 0
        while (true) {
            botClient.health()
            count++

            if (count % 100 == 0) {
                logger.info { "call coHealthCall [$number / $count]" }
            }
        }
    }
}
