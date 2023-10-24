package com.rockar.android.marvelapp.plugins

import com.rockar.android.marvelapp.dependencies.GradleConfigVersions
import com.rockar.android.marvelapp.dependencies.HiltDependencies
import com.rockar.android.marvelapp.dependencies.Plugins
import com.rockar.android.marvelapp.dependencies.ProjectDependencies
import com.rockar.android.marvelapp.utils.android
import com.rockar.android.marvelapp.utils.kotlinOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

class MarvelFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        configurePlugins(target)
        configureAndroidLibrary(target)
        configureDependencies(target)
    }

    private fun configurePlugins(target: Project) {
        with(target.pluginManager) {
            apply(Plugins.androidLibrary)
            apply(Plugins.kotlinAndroid)
            apply(Plugins.kotlinKapt)
            apply(Plugins.hiltAndroid)
        }
    }

    private fun configureAndroidLibrary(target: Project) {
        target.android {
            compileSdk = GradleConfigVersions.compileSdk

            defaultConfig {
                minSdk = GradleConfigVersions.minSdk

                testInstrumentationRunner = ProjectDependencies.testInstrumentationRunner
                consumerProguardFiles("consumer-rules.pro")
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro",
                    )
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            kotlinOptions {
                jvmTarget = GradleConfigVersions.jvmTarget
            }
        }
    }

    private fun configureDependencies(target: Project) {
        with(target.configurations.get("implementation").dependencies) {
            add(target.dependencies.create(HiltDependencies.hiltAndroid))
        }
        with(target.configurations.get("kapt").dependencies) {
            add(target.dependencies.create(HiltDependencies.hiltCompiler))
        }
    }
}
