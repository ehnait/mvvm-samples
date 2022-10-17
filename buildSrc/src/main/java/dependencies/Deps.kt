package dependencies

object Versions {
    //defaultConfig
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
    //Test
    const val testJunitVersion = "4.13.2"
    const val androidTestJunitAndroidExt = "1.1.3"
    const val androidTestEspressoCore = "3.4.0"
    //Kotlin
    const val coroutines = "1.6.4"
    //Jetpack
    const val appcompat = "1.5.1"
    const val material = "1.6.1"
    const val core = "1.9.0"
    const val lifecycle = "2.5.1"
    const val navigation = "2.5.2"
    const val hilt = "2.44"
    const val constraintlayout = "2.1.4"
    const val swiperefreshlayout = "1.1.0"
    const val paging = "3.1.1"
    const val startup = "1.1.1"
    const val room = "2.4.3"
    const val work = "2.7.1"
    const val browser = "1.4.0"
    //GitHub
    const val fresco = "2.6.0"
    const val retrofit = "2.9.0"
    const val okhttp_logging_interceptor = "4.1.0"
    const val baseRecyclerViewAdapterHelper = "3.0.4"
    const val persistentCookieJar = "v1.0.1"
    const val material_dialogs = "3.3.0"
    const val smartrefreshlayout = "1.1.3"
    const val multiStateView = "2.2.0"
    const val permissionx = "1.7.1"
    const val statusBarUtil = "1.5.1"
    const val autoSize = "v1.2.1"
    const val lottie = "5.2.0"
    const val ycWebView = "1.4.0" // 1.4.0 添加阿里https+dns解析版本 1.2.4普通版本
    const val shadowLayout = "3.2.3"
    const val photoView = "2.1.3"
    const val mmkv="1.2.14"
    const val timber = "5.0.1"

    //SDK
    const val firebase_bom = "30.3.1"

}

object Deps {

    object Test {
        const val testJunit = "junit:junit:${Versions.testJunitVersion}"
        const val androidTestJunit = "androidx.test.ext:junit:${Versions.androidTestJunitAndroidExt}"
        const val androidTestEspresso =
            "androidx.test.espresso:espresso-core:${Versions.androidTestEspressoCore}"
    }

    object  Kotlin {
        const val kotlinx_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Jetpack {
        // <https://developer.android.com/kotlin/ktx/extensions-list#androidxactivity>
        // <https://developer.android.com/jetpack/androidx/releases/activity>
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val core_ktx = "androidx.core:core-ktx:${Versions.core}"
        const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigation_dynamic_features_ktx = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}" // Feature module Support
        const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val startup = "androidx.startup:startup-runtime:${Versions.startup}"
        const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
        const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
        const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
        const val work_runtime_ktx = "androidx.work:work-runtime-ktx:${Versions.work}"
        const val browser = "androidx.browser:browser:${Versions.browser}"
    }

    object GitHub {
        // network
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okhttp_logging_interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
        const val persistentCookieJar = "com.github.franmontiel:PersistentCookieJar:${Versions.persistentCookieJar}"
        // ------
        const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
        const val autoSize = "com.github.JessYanCoding:AndroidAutoSize:${Versions.autoSize}"
        const val mmkv="com.tencent:mmkv:${Versions.mmkv}"
        const val ycWebView = "cn.yc:WebViewLib:${Versions.ycWebView}"
        const val baseRecyclerViewAdapterHelper =
            "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.baseRecyclerViewAdapterHelper}"
        const val material_dialogs_core = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
        const val material_dialogs_input = "com.afollestad.material-dialogs:input:${Versions.material_dialogs}"
        const val smartrefreshlayout = "com.scwang.smartrefresh:SmartRefreshLayout:${Versions.smartrefreshlayout}"
        const val permissionx = "com.guolindev.permissionx:permissionx:${Versions.permissionx}"
        const val statusBarUtil = "com.jaeger.statusbarutil:library:${Versions.statusBarUtil}"
        const val shadowLayout = "com.github.lihangleo2:ShadowLayout:${Versions.shadowLayout}"
        const val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"
        const val fresco = "com.facebook.fresco:fresco:${Versions.fresco}"
        const val multiStateView = "com.github.Kennyc1012:MultiStateView:${Versions.multiStateView}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    }

    object SDK {
        const val firebase_bom = "com.google.firebase:firebase-bom::${Versions.firebase_bom}"
    }

}