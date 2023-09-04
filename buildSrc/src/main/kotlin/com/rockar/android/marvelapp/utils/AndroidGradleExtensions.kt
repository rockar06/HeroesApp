package com.rockar.android.marvelapp.utils

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

val Project.`android`: LibraryExtension
    get() =
        (this as ExtensionAware).extensions.getByName("android") as LibraryExtension

fun Project.`android`(configure: Action<LibraryExtension>): Unit =
    (this as ExtensionAware).extensions.configure("android", configure)

fun LibraryExtension.`kotlinOptions`(configure: Action<KotlinJvmOptions>): Unit =
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)
