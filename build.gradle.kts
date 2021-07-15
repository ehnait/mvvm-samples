// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
        mavenCentral()
        gradlePluginPortal()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath(kotlin("gradle-plugin", version = Versions.kotlinVersion))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}")
        // MobSDK
        classpath("com.mob.sdk:MobSDK:+")

    }

}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        gradlePluginPortal()
        maven {
            setUrl("https://jitpack.io")
        }
        maven {
            setUrl("https://dl.bintray.com/umsdk/release")
        }
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
//
//tasks.register<Delete>("clean") {
//    delete(rootProject.buildDir)
//}
