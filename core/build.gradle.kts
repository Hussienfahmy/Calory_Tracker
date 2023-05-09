apply {
    from("$rootDir/base-module.gradle")
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "${ProjectConfig.appId}.core"
}

dependencies {
    "implementation"(Kotlin.serialization)
    "implementation"(AndroidX.dataStore)
}