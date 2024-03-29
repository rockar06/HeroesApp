package com.android.marvelapp.plugins

import com.android.marvelapp.dependencies.GradleConfigVersions
import com.android.marvelapp.dependencies.HiltDependencies
import com.android.marvelapp.dependencies.Plugins
import com.android.marvelapp.dependencies.ProjectDependencies
import com.android.marvelapp.utils.android
import com.android.marvelapp.utils.implementation
import com.android.marvelapp.utils.kapt
import com.android.marvelapp.utils.kotlinOptions
import com.android.marvelapp.utils.lintChecks
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

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
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro",
                    )
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            kotlinOptions {
                jvmTarget = GradleConfigVersions.jvmTarget
            }
        }
    }

    private fun configureDependencies(target: Project) {
        target.dependencies {
            implementation(HiltDependencies.hiltAndroid)
            implementation(project(":utils"))

            kapt(HiltDependencies.hiltCompiler)

            lintChecks(project(":rules"))
        }
    }
}
