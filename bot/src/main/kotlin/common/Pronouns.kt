package org.pepper.bot.common

import com.kotlindiscord.kord.extensions.commands.application.slash.converters.ChoiceEnum

enum class Pronouns(
    override val readableName: String,
    val they: String,
    val them: String,
    val their: String,
    val theirs: String,
    val are: String
) : ChoiceEnum {
    THEYTHEM("They/Them", "they", "them", "their", "theirs", "are"),
    SHEHER("She/Her", "she", "her", "her", "hers", "is"),
    HEHIM("He/Him", "he", "him", "his", "his", "is");
}