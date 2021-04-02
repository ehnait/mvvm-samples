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


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // KOTLIN
    api(KotlinLibraries.kotlin)
    api(KotlinLibraries.coreKtx)
    api(KotlinLibraries.kotlinCoroutineCore)
    api(KotlinLibraries.kotlinCoroutineAndroid)
    // Http
    api(OtherLibraries.retrofit)
    api(OtherLibraries.retrofitGsonConverter)
    api(OtherLibraries.loggingInterceptor)
}
