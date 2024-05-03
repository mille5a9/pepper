package org.pepper.bot

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import kotlinx.coroutines.coroutineScope

suspend fun main(): Unit = coroutineScope {
    val bot = ExtensibleBot(env("token")) {
    }

    bot.start()
}