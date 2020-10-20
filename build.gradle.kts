// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        gradlePluginPortal()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
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
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
