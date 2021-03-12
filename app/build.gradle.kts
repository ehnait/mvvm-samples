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

    // APK 签名的那些事：https://www.jianshu.com/p/a1f8e5896aa2
    signingConfigs {
        getByName("debug") {
            storeFile = file("../debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }

        create("release") {
            storeFile = file("../debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isJniDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isZipAlignEnabled = false
//            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isDebuggable = false
            isJniDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            isZipAlignEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    android.applicationVariants.all {
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                this.outputFileName = "graduation@app_$versionName.apk"

            }
        }
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
    implementation(project(Modules.umeng))

//    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
}

