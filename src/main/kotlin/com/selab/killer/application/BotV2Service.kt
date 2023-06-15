package com.selab.killer.application

import com.selab.killer.client.BotClient
import com.selab.killer.common.async.ExecutorGenerator
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * 멀티 스레드 기반
 */
@Service
class BotV2Service(
    private val botClient: BotClient
) {
    private val logger = mu.KotlinLogging.logger { }

    @Async(value = "taskExecutor")
    fun executeKiller() {
        killServer()
        logger.info { "killer V2 start / no stop...." }
    }

    fun killServer() {
        val threadManager = ExecutorGenerator(
            threadName = "bot-killer",
            corePoolSize = 10,
            maxPoolSize = 20,
            queueCapacity = 10
        ).generate()

        (0..20).forEach {
            threadManager.execute {
                runBlocking {
                    healthCall(it)
                }
            }
        }
    }

    private suspend fun healthCall(number: Int) {
        var count = 0
        while (true) {
            botClient.health()
            count++

            if (count % 100 == 0) {
                logger.info { "call asyncHealthCall [$number / $count]" }
            }
        }
    }
}
