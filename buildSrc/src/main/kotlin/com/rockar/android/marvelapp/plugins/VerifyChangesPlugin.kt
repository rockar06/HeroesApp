package com.rockar.android.marvelapp.plugins

import com.rockar.android.marvelapp.tasks.VerifyChangesTask
import com.rockar.android.marvelapp.utils.FileUtils.getModulesChanged
import com.rockar.android.marvelapp.utils.coverage.CoverageUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class VerifyChangesPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        if (target.rootProject == target) {
            target.tasks.register("verifyChanges", VerifyChangesTask::class.java) {

                val projectsToEvaluate = if (target.hasProperty("localChanges")) {
                    target.subprojects
                } else {
                    getLocalTaskToExecute(target)
                }


                target.logger.lifecycle("=============== Task to execute ===============")

                val taskToExecute = projectsToEvaluate.flatMap { subProject ->
                    subProject.tasks.filter {
                        isValidTaskToBeAdded(subProject.name, it.name, target)
                    }.map {
                        ":${subProject.name}:${it.name}"
                    }
                }

                if (taskToExecute.isNotEmpty()) {
                    target.logger.lifecycle(
                        taskToExecute.joinToString(
                            separator = "\n - ",
                            prefix = " - ",
                        ),
                    )
                } else {
                    target.logger.lifecycle(" - No tasks to execute")
                }

                dependsOn(taskToExecute)
            }
        }
    }

    private fun getLocalTaskToExecute(target: Project): Set<Project> {
        val projectChanges = getModulesChanged(target)

        target.logger.lifecycle("=============== Affected Files ===============")
        if (projectChanges.affectedFiles.isNotEmpty()) {
            target.logger.lifecycle(
                projectChanges.affectedFiles.joinToString(
                    separator = "\n - ",
                    prefix = " - ",
                ),
            )
        } else {
            target.logger.lifecycle(" - No changes found")
        }

        target.logger.lifecycle("=============== Affected Modules ===============")
        if (projectChanges.affectedModules.isNotEmpty()) {
            target.logger.lifecycle(
                projectChanges.affectedModules.joinToString(
                    separator = "\n - ",
                    prefix = " - ",
                ) { it.name },
            )
        } else {
            target.logger.lifecycle(" - No modules found")
        }

        return projectChanges.affectedModules
    }

    private fun isValidTaskToBeAdded(
        projectName: String,
        taskName: String,
        rootProject: Project,
    ): Boolean {
        var validTask = false

        val testPatterns = if (!CoverageUtils.getIgnoreModules().contains(projectName)) {
            listOf(
                Regex("test.*DebugUnitTest"),
                Regex("test.*DebugUnitTestCoverage"),
            )
        } else {
            listOf(Regex("test.*DebugUnitTest"))
        }

        validTask = validTask || testPatterns.any { taskName.matches(it) }

        if (taskName.matches(Regex("test.*DebugUnitTestCoverageVerification"))) {
            validTask = rootProject.hasProperty("verifyCoverage")
        }

        val codeIntegrityTask = if (rootProject.hasProperty("formatCode")) {
            "formatCode"
        } else {
            "checkCodeFormat"
        }

        if (taskName == codeIntegrityTask) {
            validTask = true
        }

        return validTask
    }
}
