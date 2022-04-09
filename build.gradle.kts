import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
    application
    id("io.freefair.lombok") version "6.4.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

group = "xyz.motz"
version = "1.0.0-SNAPSHOT"
val apiVersion = "1.18"

application {
    mainClass.set("xyz.motz.classe.Classe")
}

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://papermc.io/repo/repository/maven-public")
}

dependencies {
    // Minecraft specific
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT") // PAPER API

    compileOnly("dev.jorel.CommandAPI:commandapi-core:6.5.4") // CommandAPI

    compileOnly("de.tr7zw:item-nbt-api-plugin:2.9.2") // NBTAPI

    implementation("net.kyori:adventure-text-minimessage:4.10.1") // MiniMessage

    implementation("com.github.retrooper:packetevents:v1.8.3") // PacketEvents

    // not minecraft specific
    compileOnly("org.projectlombok:lombok:1.18.22") // Lombok
    annotationProcessor("org.projectlombok:lombok:1.18.22") // Lombok
}

tasks.processResources {
    outputs.upToDateWhen { false }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    from("src/main/resources") {
        filter(ReplaceTokens::class, "tokens" to mapOf("version" to version, "apiVersion" to apiVersion))
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.runServer {
    minecraftVersion("1.18.2")
}