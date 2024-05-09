package org.pepper.bot.extensions

import com.kotlindiscord.kord.extensions.annotations.UnexpectedBehaviour
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.impl.*
import com.kotlindiscord.kord.extensions.components.components
import com.kotlindiscord.kord.extensions.components.linkButton
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.ephemeralSlashCommand
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.createMessage
import dev.kord.rest.builder.message.embed

// allows stamping out new text channel with some avenue of allowing
// user assignment to a related role to opt-in
class ShareExtension : Extension() {
    override val name: String
        get() = "share"

    @OptIn(UnexpectedBehaviour::class)
    override suspend fun setup() {
        ephemeralSlashCommand(::TripArgs) {
            name = "trip"
            description = "Share a Google Maps trip with Discord user(s)"

            guild(Snowflake(env("testserver")))

            action {
                val link = arguments.body.whileReversed { it.substring(0, it.indexOf(' ')) }
                arguments.user.getDmChannel().createMessage {
                    components {
                        content =  "${user.mention} has shared their trip with you via Maps"
                        linkButton {
                            url = link
                            label = "See on Google Maps"
                        }
                    }
                }

            }
        }
    }

    inner class TripArgs : Arguments() {
        val user by user {
            name = "user"
            description = "Person to share trip with"
        }

        val body by string {
            name = "body"
            description = "The canned text generated from the app"
        }
    }

    private fun String.whileReversed(func: (String) -> String): String = this.reversed().let(func).reversed()
}