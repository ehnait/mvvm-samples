import org.gradle.api.internal.tasks.compile.JavaCompilerArgumentsBuilder.LOGGER

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
    kotlin("android.extensions")
    id("com.mob.sdk")
}
MobSDK {
    appKey(findProperty("MobSDK_Key").toString())
    appSecret(findProperty("MobSDK_Secret").toString())
    SMSSDK { }
}

android {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        applicationId = ApplicationId.id
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Releases.versionCode
        versionName = Releases.versionName
        multiDexEnabled =true
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }

        // Apply fake signing config to release to test "assembleRelease" locally
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
//            proguardFile(file("../config/proguard/proguard-rules.txt"))
//            signingConfig = signingConfigs.getByName("debug")
        }
    }

    signingConfigs {
        getByName("debug") {
//            storeFile = file("../config/signing/debug.keystore")
//            storePassword = signing.pass
//            keyAlias = signing.alias
//            keyPassword = signing.pass
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // KOTLIN
    implementation(KotlinLibraries.kotlin)
    implementation(KotlinLibraries.kotlinCoroutineCore)
    implementation(AndroidLibraries.kotlinCoroutineAndroid)
    // ANDROID
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.multidex)
    implementation(AndroidLibraries.coreKtx)
    implementation(AndroidLibraries.constraintLayout)
    implementation(AndroidLibraries.recyclerView)
    implementation(AndroidLibraries.material)
    //Libraries
    implementation(Libraries.coil)
    implementation(Libraries.BaseRecyclerViewAdapterHelper)
    implementation(Libraries.logger)
    //project
    implementation(project(Modules.common))
    implementation(project(Modules.http))
//
//    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.1")
}
