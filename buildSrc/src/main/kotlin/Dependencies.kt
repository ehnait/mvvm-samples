object ApplicationId {
    val id = "com.galaxy.graduationproject2011"
}

object Modules {
    val app = ":app"
    val common = ":common"
    val http = ":http"
    val repository = ":data:repository"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object signing {
    const val alias = "androiddebugkey"
    const val pass = "android"
}

object Versions {
    val compileSdk = 30
    val minSdk = 19
    val targetSdk = 30
    val gradle = "4.0.0"

    val kotlin = "1.4.10"
    val coroutines = "1.3.9"

    val appCompat = "1.2.0"
    val coreKtx = "1.3.1"
    val constraintLayout = "2.0.1"
    val recyclerview = "1.0.0"
    val material = "1.2.0"

    val room = "2.1.0-alpha06"
    val retrofit = "2.9.0"
    val retrofitCoroutines = "0.9.2"
    val retrofitGson = "2.9.0"
    val gson = "2.8.5"
    val okHttp = "3.12.1"
    val coil = "1.0.0-rc3"
    val logger = "4.7.1"
    val customactivityoncrash = "2.3.0"
    val stetho = "1.5.1"
    val leakcanary = "2.3"

}

object Libraries {
    // ROOM
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // RETROFIT
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // COIL https://github.com/coil-kt/coil
    val coil = "io.coil-kt:coil:${Versions.coil}"

    //Logger https://github.com/JakeWharton/timber
    val logger = "com.jakewharton.timber:timber:${Versions.logger}"

    // 本地异常捕捉框架：https://github.com/Ereza/CustomActivityOnCrash
    val customactivityoncrash = "cat.ereza:customactivityoncrash:${Versions.customactivityoncrash}"

    //Facebook开源的一个Android调试工具 http://facebook.github.io/stetho/    chrome://inspect/#devices
    val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

    //Android的内存泄漏检测库//debugImplementation because LeakCanary should only run in debug builds.  https://square.github.io/leakcanary/
    val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"

}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object AndroidLibraries {
    // KOTLIN
    val kotlinCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // ANDROID
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val material = "com.google.android.material:material:${Versions.material}"
}

