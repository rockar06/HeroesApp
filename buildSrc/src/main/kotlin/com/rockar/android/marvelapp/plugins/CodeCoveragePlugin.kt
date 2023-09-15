package com.rockar.android.marvelapp.plugins

import com.rockar.android.marvelapp.utils.androidLibrary
import com.rockar.android.marvelapp.utils.capitalizeFirst
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.language.base.plugins.LifecycleBasePlugin
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File

class CodeCoveragePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        if (target.rootProject == target) {
            target.subprojects {
                afterEvaluate {
                    applyJacocoPluginToProject(this)
                    configureJacocoTask(this)
                }
            }
        }
    }

    private fun applyJacocoPluginToProject(project: Project) {
        project.pluginManager.apply("jacoco")
    }

    private fun configureJacocoTask(project: Project) {
        project.androidLibrary?.buildTypes?.forEach {
            val testTaskName = "test${it.name.capitalizeFirst()}UnitTest"
            createCoverageTask(project, testTaskName)
            createCoverageVerificationTask(project, testTaskName)
        }
    }

    private fun createCoverageTask(project: Project, testTaskName: String) {
        val coverageTaskName = "${testTaskName}Coverage"
        project.tasks.register(coverageTaskName, JacocoReport::class.java) {

            group = LifecycleBasePlugin.VERIFICATION_GROUP
            description = "Generate Jacoco coverage report for $testTaskName"

            dependsOn(testTaskName)

            reports.html.required.set(true)

            classDirectories.setFrom(buildClassDirectories(project))
            sourceDirectories.setFrom(DEFAULT_SOURCE_DIRECTORIES)
            executionData(File(getBuildDir(project), "jacoco/${testTaskName}.exec"))
        }
    }

    private fun createCoverageVerificationTask(project: Project, testTaskName: String) {
        val coverageTaskName = "${testTaskName}Coverage"
        val coverageVerificationTaskName = "${coverageTaskName}Verification"
        project.tasks.register(coverageVerificationTaskName, JacocoCoverageVerification::class.java) {

            group = LifecycleBasePlugin.VERIFICATION_GROUP
            description = "Verify Jacoco coverage for $testTaskName"

            dependsOn(coverageTaskName)

            violationRules {
                rule {
                    element = "BUNDLE"
                    limit {
                        counter = "LINE"
                        value = "COVEREDRATIO"
                        minimum = 0.90.toBigDecimal()
                    }
                    limit {
                        counter = "BRANCH"
                        value = "COVEREDRATIO"
                        minimum = 0.80.toBigDecimal()
                    }
                }
            }

            classDirectories.setFrom(buildClassDirectories(project))
            sourceDirectories.setFrom(DEFAULT_SOURCE_DIRECTORIES)
            executionData(File(getBuildDir(project), "jacoco/${testTaskName}.exec"))
        }
    }

    private fun buildClassDirectories(project: Project): MutableIterable<*> {
        return project.fileTree("${getBuildDir(project)}$DEFAULT_FILE_TREE_PATH").apply {
                setExcludes(DEFAULT_EXCLUDES)
            }.toMutableList()
    }

    private fun getBuildDir(project: Project): File {
        return project.layout.buildDirectory.asFile.get()
    }

    companion object {

        private val DEFAULT_FILE_TREE_PATH = "/tmp/kotlin-classes/debug"

        private val DEFAULT_EXCLUDES = listOf(
            "**/databinding/*Binding.*",
            "**/R.class",
            "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*",
            "android/**/*.*",
            "**/Lambda$*.class",
            "**/Lambda.class",
            "**/*Lambda.class",
            "**/*Lambda*.class",
            "**/*_MembersInjector.class",
            "**/Dagger*Component*.*",
            "**/*Module_*Factory.class",
            "**/di/module/*",
            "**/*_Factory*.*",
            "**/*Module*.*",
            "**/*Dagger*.*",
            "**/*Hilt*.*",
            // kotlin
            "**/*MapperImpl*.*",
            "**/BuildConfig.*",
            "**/*Component*.*",
            "**/*BR*.*",
            "**/Manifest*.*",
            "**/*Companion*.*",
            "**/*Module*.*",
            "**/*Dagger*.*",
            "**/*Hilt*.*",
            "**/*MembersInjector*.*",
            "**/*_MembersInjector.class",
            "**/*_Factory*.*",
            "**/*_Provide*Factory*.*",
            "**/*Extensions*.*"
        )

        private val DEFAULT_SOURCE_DIRECTORIES = listOf(
            "src/main/java",
            "src/main/kotlin",
        )
    }
}
