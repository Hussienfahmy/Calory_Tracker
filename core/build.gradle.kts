apply {
    from("$rootDir/base-module.gradle")
}

plugins {
    id("com.android.library")
}

android {
    namespace = "${ProjectConfig.appId}.core"
}

dependencies {

}