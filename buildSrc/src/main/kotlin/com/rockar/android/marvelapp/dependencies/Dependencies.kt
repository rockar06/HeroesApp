package com.rockar.android.marvelapp.dependencies

object Plugins {
    const val hiltAndroid = "com.google.dagger.hilt.android"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "org.jetbrains.kotlin.kapt"
    const val verifyChanges = "com.rockar.android.plugins.verify"
}

object HiltDependencies {
    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltAndroidPluginVersion"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltAndroidPluginVersion"
}

object ProjectDependencies {
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appcompatVersion"
    const val googleMaterial = "com.google.android.material:material:$materialVersion"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val moshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    private const val baseOkHttp = "com.squareup.okhttp3"
    const val okHttpBom = "$baseOkHttp:okhttp-bom:$okHttpBomVersion"
    const val okHttpClient = "$baseOkHttp:okhttp"
    const val okHttpLogger = "$baseOkHttp:logging-interceptor"
}

object NetworkDependencies {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:2.9.0"
    const val okHttpClient = "com.squareup.okhttp3:okhttp:4.10.0"
    const val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:4.10.0"
}

object TestDependencies {
    const val jUnit = "junit:junit:$jUnitVersion"
    const val jUnitInstrumented = "androidx.test.ext:junit:$jUnitInstrumentedVersion"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
    const val truthLibrary = "com.google.truth:truth:$truthVersion"
    const val mockkLibrary = "io.mockk:mockk:$mockkVersion"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}
