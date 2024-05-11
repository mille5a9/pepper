package org.pepper.bot.common

enum class Pronouns(val they: String, val them: String, val their: String, val theirs: String, val are: String) {
    THEYTHEM("they", "them", "their", "theirs", "are"),
    SHEHER("she", "her", "her", "hers", "is"),
    HEHIM("he", "him", "his", "his", "is");
}