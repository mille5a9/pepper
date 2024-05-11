package org.pepper.bot.extensions

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.converters.impl.defaultingEnumChoice
import com.kotlindiscord.kord.extensions.commands.converters.impl.*
import com.kotlindiscord.kord.extensions.components.components
import com.kotlindiscord.kord.extensions.components.linkButton
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.ephemeralSlashCommand
import com.kotlindiscord.kord.extensions.utils.capitalizeWords
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.createMessage
import dev.kord.rest.builder.message.embed
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toLocalDateTime
import mu.KLogging
import org.pepper.bot.common.Pronouns
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class ShareExtension : Extension() {
    companion object : KLogging()
    override val name: String
        get() = "share"

    override suspend fun setup() {
        ephemeralSlashCommand(::TripArgs) {
            name = "trip"
            description = "Share a Google Maps trip with a Discord user"
            allowInDms = true

            if (env("environment") == "DEV") guild(Snowflake(env("testserver")))

            action {
                val link = arguments.body.whileReversed { it.substring(0, it.indexOf(' ')) }
                arguments.user.getDmChannel().createMessage {
                    components {
                        content = "Woof!"
                        embed {
                            title = "${user.asUser().tag} has shared ${arguments.pronouns.their} trip with you via Maps"
                            description = "${arguments.pronouns.they.capitalizeWords()} ${arguments.pronouns.are} going to take about " +
                                    "${arguments.durationMins} minutes to get to ${arguments.destination}."
                            field {
                                value = "ETA: " + arguments.durationMins.toEta()
                            }
                            footer {
                                text = "${arguments.pronouns.they.capitalizeWords()} will absolutely be driving safely :)"
                            }
                        }
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
            description = "Any text that ends with a Maps link"
        }

        val durationMins by int {
            name = "duration"
            description = "Number of minutes the trip will take"
        }

        val destination by string {
            name = "destination"
            description = "Name or address of trip destination"
        }

        val pronouns by defaultingEnumChoice<Pronouns> {
            name = "pronouns"
            description = "Your own preferred pronouns"
            defaultValue = Pronouns.THEYTHEM
            typeName = "Pronouns"
        }
    }

    private fun Int.toEta(): String {
        val currentDate =
            Date().toInstant().plusSeconds(this * 60L).atZone(ZoneId.of("America/New_York")).toLocalDateTime()
        val dayNumSuffix = when (currentDate.dayOfMonth) { 1 -> "st"; 2 -> "nd"; 3 -> "rd"; else -> "th" }
        val formatter = DateTimeFormatter.ofPattern("hh:mm a MMM dd")
        return currentDate.format(formatter).plus(dayNumSuffix)
    }

    private fun String.whileReversed(func: (String) -> String): String = this.reversed().let(func).reversed()
}