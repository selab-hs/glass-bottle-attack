package com.selab.killer.application

import com.selab.killer.client.BotClient
import com.selab.killer.client.BotLoginRequest
import com.selab.killer.client.BotSignRequest
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

    suspend fun check() = botClient.health()


    @Async(value = "taskExecutor")
    fun coExecuteKiller() {
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

    suspend fun sign(request: BotSignRequest) = withContext(Dispatchers.IO) { botClient.sign(request) }

    suspend fun login(request: BotLoginRequest) = withContext(Dispatchers.IO) { botClient.login(request) }

    suspend fun coKillServer() {
        val signedUsers = sign()
        logger.info { "sign success / count : ${signedUsers.size}" }
    }

    // sign
    suspend fun sign(): List<Pair<String, String>> {
        val users = (1..100).map {
            BotSignRequest(
                email = "computer$it@naver.com",
                mbti = it.toLong(),
                password = "computer-------$it"
            )
        }.also {
            it.forEach {
                kotlin.runCatching {
                    sign(it)
                }.onFailure { logger.error { it } }
                    .getOrNull()
            }
        }

        return users.map { it.email to it.password }
    }
}
