object ApplicationId {
    val id = "com.galaxy.graduationproject2011"
}

object Modules {
    const val app = ":app"
    const val common = ":common"
    const val http = ":http"
    const val umeng = ":umeng"
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

    const val kotlinVersion = "1.4.10"
    const val coroutinesVersion = "1.3.9"

    const val appCompatVersion = "1.2.0"
    const val lifecycleVersion = "2.2.0"
    const val multidexVersion = "2.0.1"
    const val coreKtxVersion = "1.3.1"
    const val constraintLayoutVersion = "2.0.1"
    const val recyclerviewVersion = "1.0.0"
    const val navigationVersion = "2.3.1"
    const val materialVersion = "1.2.0"

    const val retrofitVersion = "2.9.0"
    const val gsonVersion = "2.8.5"
    const val okHttpVersion = "3.14.9"
    const val coilVersion = "1.0.0-rc3"
    const val lottieVersion = "3.5.0"
    const val BaseRecyclerViewAdapterHelperVersion = "3.0.6"

}

object AndroidLibraries {
    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"

    //lifecycle
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"     //Lifecycles only (without ViewModel or LiveData)
    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"    //LiveData
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"   //ViewModel
    const val lifecycleViewModelSavedstate =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:$${Versions.lifecycleVersion}"     // Saved state module for ViewModel
    const val lifecycleCommonJava8 =
        "androidx.lifecycle:lifecycle-common-java8:$${Versions.lifecycleVersion}"     // Annotation processor for Java8
    const val lifecycleService =
        "androidx.lifecycle:lifecycle-service:$${Versions.lifecycleVersion}"  // optional - helpers for implementing LifecycleOwner in a Service
    const val lifecycleProcess =
        "androidx.lifecycle:lifecycle-process:$${Versions.lifecycleVersion}"  // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process

    const val multidex = "androidx.multidex:multidex:${Versions.multidexVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"

    //NAVIGATION
    const val navigationFragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUi_ktx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
}

object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val kotlinCoroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val kotlinCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
}

object OtherLibraries {
    // RETROFIT
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val httpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"

    // COIL https://github.com/coil-kt/coil
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    const val BaseRecyclerViewAdapterHelper =
        "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.BaseRecyclerViewAdapterHelperVersion}"
}