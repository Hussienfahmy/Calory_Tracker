plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
    id(Build.hiltAndroidGradlePlugin) version Build.hiltAndroidGradlePluginVersion apply false
}