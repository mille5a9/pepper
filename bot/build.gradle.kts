import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "1.9.23"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.pepper.bot"
version = "1.0"

val kordVersion: String by project
val kordExVersion: String by project
val kotlinxCoroutinesVersion: String by project
val logbackVersion: String by project
val logbackGroovyVersion: String by project
val loggingVersion: String by project

repositories {
    google()
    mavenCentral()

    maven {
        name = "Sonatype Snapshots (Legacy)"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }

    maven {
        name = "Sonatype Snapshots"
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:${kordExVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")
    implementation("io.github.virtualdogbert:logback-groovy-config:${logbackGroovyVersion}")
    implementation("io.github.oshai:kotlin-logging:${loggingVersion}")

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

tasks.jar {
    manifest.attributes["Main-Class"] = "org.pepper.bot.MainKt"
}