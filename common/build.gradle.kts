plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}
android {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }
    buildTypes {
        getByName("debug") {

        }
        // Apply fake signing config to release to test "assembleRelease" locally
        getByName("release") {
            isMinifyEnabled = true
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // COMMON
    implementation(Libraries.logger)
    implementation(Libraries.customactivityoncrash)
    implementation(Libraries.stetho)
    api(Libraries.stethoOkhttp)
    implementation(Libraries.leakcanary)
}


