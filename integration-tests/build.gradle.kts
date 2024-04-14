import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.gatling.gradle") version "3.10.5.1"
    kotlin("jvm") version "1.9.23"
}

group = "com.github.dhslrl321"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
