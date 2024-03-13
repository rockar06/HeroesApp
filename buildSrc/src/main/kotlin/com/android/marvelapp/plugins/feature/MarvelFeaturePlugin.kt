package com.android.marvelapp.plugins.feature

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.android.marvelapp.dependencies.GradleConfigVersions
import com.android.marvelapp.dependencies.HiltDependencies
import com.android.marvelapp.dependencies.Plugins
import com.android.marvelapp.dependencies.ProjectDependencies
import com.android.marvelapp.utils.implementation
import com.android.marvelapp.utils.kapt
import com.android.marvelapp.utils.kotlinOptions
import com.android.marvelapp.utils.lintChecks
import com.android.marvelapp.utils.techdebt.TechDebtDeclaration
import com.android.marvelapp.utils.techdebt.isExpired
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class MarvelFeaturePlugin : Plugin<Project> {

    private lateinit var extension: FeaturePluginExtension

    override fun apply(target: Project) {
        extension = target.extensions.create<FeaturePluginExtension>("featureConfig")
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

    @Suppress("UnstableApiUsage")
    private fun configureAndroidLibrary(target: Project) {
        target.extensions.configure<LibraryExtension> {
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

        target.extensions.getByType<LibraryAndroidComponentsExtension>()
            .finalizeDsl { libraryExtension ->
                libraryExtension.lint {
                    disable += TechDebtDeclaration.techDebtList().filter { techDebtItem ->
                        extension.getTechDebtObject().delayedRules.any { it == techDebtItem.lintRuleName } && !techDebtItem.isExpired()
                    }.map { it.lintRuleName }
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
