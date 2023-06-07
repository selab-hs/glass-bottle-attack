package com.selab.killer.resource

import com.selab.killer.application.BotService
import org.springframework.web.bind.annotation.RestController

@RestController
class BotResource(
    private val botService: BotService
) {
}
