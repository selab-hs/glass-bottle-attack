package com.selab.killer.batch

import org.springframework.stereotype.Component

@Component
class BotScheduler(
    private val botJob: BotJob
) {
}
