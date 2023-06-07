package com.selab.glassbottleattack.resource

import com.selab.glassbottleattack.application.BotService
import org.springframework.web.bind.annotation.RestController

@RestController
class BotResource(
    private val botService: BotService
) {
}
