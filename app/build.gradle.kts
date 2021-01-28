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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ndk {
            abiFilters("armeabi-v7a", "arm64-v8a")
        }
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
    // Test
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    // KOTLIN
    implementation(KotlinLibraries.kotlin)
    implementation(KotlinLibraries.coreKtx)
    implementation(KotlinLibraries.kotlinCoroutineCore)
    implementation(KotlinLibraries.kotlinCoroutineAndroid)
    // ANDROID
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.lifecycleRuntime)
    implementation(AndroidLibraries.lifecycleLiveData)
    implementation(AndroidLibraries.lifecycleViewModel)
    implementation(AndroidLibraries.multidex)
    implementation(AndroidLibraries.constraintLayout)
    implementation(AndroidLibraries.recyclerView)
    implementation(AndroidLibraries.navigationFragment_ktx)
    implementation(AndroidLibraries.navigationUi_ktx)
    implementation(AndroidLibraries.material)
    //Libraries
    implementation(OtherLibraries.coil)
    implementation(OtherLibraries.lottie)
    implementation(OtherLibraries.BaseRecyclerViewAdapterHelper)
    //project
    implementation(project(Modules.common))
    implementation(project(Modules.http))

//    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
}
