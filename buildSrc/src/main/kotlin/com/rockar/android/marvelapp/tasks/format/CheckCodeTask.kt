package com.rockar.android.marvelapp.tasks.format

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.language.base.plugins.LifecycleBasePlugin

abstract class CheckCodeTask : DefaultTask() {

    override fun getGroup(): String {
        return LifecycleBasePlugin.VERIFICATION_GROUP
    }

    override fun getDescription(): String {
        return "Check Kotlin code style."
    }

    @TaskAction
    fun executeTask() {
        project.javaexec {
            workingDir = project.projectDir
            classpath = project.configurations.getByName("ktlint")
            mainClass.set("com.pinterest.ktlint.Main")
            args(
                "**/src/**/*.kt", // Include any kotlin file in the project
                "**.kts", // Include any kotlin script in the project
                "!**/build/**", // exclude build folders
            )
        }
    }
}
