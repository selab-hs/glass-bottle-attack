package com.selab.killer.batch

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BotScheduler(
    private val botJob: BotJob
) {
    @Scheduled(cron = "*/3 * * * * *")
    fun attackHealthCheck() = botJob.runAttackHealthCheckJob()
}
