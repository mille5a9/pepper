plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.pepper.bot"
version = "1.0-SNAPSHOT"

val kordVersion: String by project
val kotlinxCoroutinesVersion: String by project

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.kord:kord-core:${kordVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}

application {
    mainClass = "org.pepper.bot.MainKt"
}