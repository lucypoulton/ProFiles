/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
}

dependencies {
    implementation(project(":profiles-api"))
    compileOnly("me.clip:placeholderapi:2.10.9")
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    compileOnly("me.lucyy:LucyCommonLib:1.3.0")
}

description = "profiles-papi"