package org.pepper.bot

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import kotlinx.coroutines.coroutineScope
import org.pepper.bot.extensions.NewGameExtension

suspend fun main(): Unit = coroutineScope {
    val bot = ExtensibleBot(env("token")) {
        extensions {
            add(::NewGameExtension)
        }
    }

    bot.start()
}