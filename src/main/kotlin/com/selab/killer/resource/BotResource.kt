package com.selab.killer.resource

import com.selab.killer.application.BotTestService
import com.selab.killer.application.BotV1Service
import com.selab.killer.application.BotV2Service
import com.selab.killer.client.BotLoginRequest
import com.selab.killer.client.BotSignRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BotResource(
    private val botTestService: BotTestService,
    private val botV1Service: BotV1Service,
    private val botV2Service: BotV2Service
) {
    @GetMapping("/api/v1/check")
    suspend fun check() = botV1Service.check()

    /** 코루틴 */
    @GetMapping("/api/v1/kill")
    fun killV1() = botV1Service.coExecuteKiller()

    /** 멀티스레드 */
    @GetMapping("/api/v2/kill")
    fun killV2() = botV2Service.asyncExecuteKiller()

    @GetMapping("/api/v1/sign")
    suspend fun coSign() = botV1Service.sign(
        BotSignRequest(
            email = "computer1@naver.com",
            mbti = 1,
            password = "computer-1"
        )
    )

    @GetMapping("/api/v1/login")
    suspend fun coLogin() = botV1Service.login(
        BotLoginRequest(
            email = "computer1@naver.com",
            password = "computer-1"
        )
    )

    @GetMapping("/api/v1/kill-server")
    suspend fun coKillServer() = botV1Service.coKillServer()


    // 코루틴 테스트
    @GetMapping("/test")
    suspend fun test() = botTestService.test()
}
