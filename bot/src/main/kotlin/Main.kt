package org.pepper.bot

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        println("Hello World!")
    }
}