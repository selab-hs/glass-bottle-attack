package com.selab.killer.resource

import com.selab.killer.application.BotV1Service
import com.selab.killer.application.BotV2Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BotResource(
    private val botV1Service: BotV1Service,
    private val botV2Service: BotV2Service
) {
    @GetMapping("/api/v1/kill")
    fun killV1() = botV1Service.executeKiller()

    @GetMapping("/api/v2/kill")
    fun killV2() = botV2Service.executeKiller()
}
