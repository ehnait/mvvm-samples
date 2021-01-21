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
        // Apply fake signing config to release to test "assembleRelease" locally
        getByName("release") {
            isMinifyEnabled = true
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(AndroidLibraries.appCompat)
    api(AndroidLibraries.lifecycleRuntime)
    api(AndroidLibraries.lifecycleLiveData)
    api(AndroidLibraries.lifecycleViewModel)
}


