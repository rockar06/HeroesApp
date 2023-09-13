package com.rockar.android.marvelapp.plugins

import com.rockar.android.marvelapp.tasks.VerifyChangesTask
import com.rockar.android.marvelapp.utils.FileUtils.getModulesChanged
import org.gradle.api.Plugin
import org.gradle.api.Project

class VerifyChangesPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        if (target.rootProject == target) {
            target.tasks.register("verifyChanges", VerifyChangesTask::class.java) {
                val taskToExecute = if (project.hasProperty("localChanges")) {
                    getModulesChanged(project).flatMap {
                        listOf(
                            ":${it}:checkCodeFormat",
                            ":${it}:testDebugUnitTestCoverageVerification"
                        )
                    }
                } else {
                    listOf<String>(
                        "checkCodeFormat",
                        "testDebugUnitTestCoverageVerification"
                    )
                }
                dependsOn(taskToExecute)
            }
        }
    }
}
