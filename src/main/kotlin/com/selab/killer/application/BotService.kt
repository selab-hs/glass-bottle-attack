package com.selab.killer.application

import com.selab.killer.client.BotClient
import org.springframework.stereotype.Service

@Service
class BotService(
    private val botClient: BotClient
) {
}
