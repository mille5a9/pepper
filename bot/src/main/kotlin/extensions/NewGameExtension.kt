package org.pepper.bot.extensions

import com.kotlindiscord.kord.extensions.extensions.Extension

// allows stamping out new text channel with some avenue of allowing user assignment to a related role to opt-in
class NewGameExtension : Extension() {
    override val name: String
        get() = "newgame"

    override suspend fun setup() {
        TODO("Not yet implemented")
    }

}