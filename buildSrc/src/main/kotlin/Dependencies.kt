object ApplicationId {
    val id = "com.galaxy.graduationproject2011"
}

object Modules {
    const val app = ":app"
    const val common = ":common"
    const val http = ":http"
    const val repository = ":data:repository"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val compileSdk = 30
    const val minSdk = 19
    const val targetSdk = 30
    const val gradle = "4.0.0"

    const val kotlin = "1.4.10"
    const val coroutines = "1.3.9"

    const val appCompat = "1.2.0"
    const val multidex = "2.0.1"
    const val coreKtx = "1.3.1"
    const val constraintLayout = "2.0.1"
    const val recyclerview = "1.0.0"
    const val navigation = "2.3.1"
    const val material = "1.2.0"

    const val retrofit = "2.9.0"
    const val retrofitCoroutines = "0.9.2"
    const val retrofitGson = "2.9.0"
    const val gson = "2.8.5"
    const val okHttp = "3.12.1"
    const val coil = "1.0.0-rc3"
    const val lottie = "3.5.0"
    const val BaseRecyclerViewAdapterHelper = "3.0.6"
    const val logger = "4.7.1"

}

object Libraries {
    // RETROFIT
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // COIL https://github.com/coil-kt/coil
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val BaseRecyclerViewAdapterHelper =
        "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.BaseRecyclerViewAdapterHelper}"

    //Logger https://github.com/JakeWharton/timber
    const val logger = "com.jakewharton.timber:timber:${Versions.logger}"
}

object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinCoroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object AndroidLibraries {
    // KOTLIN
    const val kotlinCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    //NAVIGATION
    const val navigationFragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val material = "com.google.android.material:material:${Versions.material}"
}

