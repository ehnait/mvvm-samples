import org.gradle.api.internal.tasks.compile.JavaCompilerArgumentsBuilder.LOGGER

//apply(from = "../ktlint.gradle.kts")
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
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
        multiDexEnabled = true
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../debug.keystore")
//            storePassword = findProperty("KeyStorePass").toString()
//            keyAlias = findProperty("KeyStoreAlias").toString()
//            keyPassword = findProperty("KeyStorePass").toString()

            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
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
    implementation(AndroidLibraries.navigationFragment_ktx)
    implementation(AndroidLibraries.navigationUi_ktx)
    implementation(AndroidLibraries.material)
    //Libraries
    implementation(Libraries.coil)
    implementation(Libraries.lottie)
    implementation(Libraries.BaseRecyclerViewAdapterHelper)
    implementation(Libraries.logger)
    //project
    implementation(project(Modules.common))
    implementation(project(Modules.http))

    // optional - Test helpers
    testImplementation ("androidx.room:room-testing:2.2.5")

//    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
}
