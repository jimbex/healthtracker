plugins {
    kotlin("jvm") version "2.2.10"
    id("org.jetbrains.dokka") version "2.0.0"
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:2.0.0")
    implementation("io.github.oshai:kotlin-logging:7.0.0")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.slf4j:slf4j-simple:2.0.16")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}