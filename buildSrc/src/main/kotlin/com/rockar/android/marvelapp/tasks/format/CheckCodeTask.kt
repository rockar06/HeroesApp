package com.rockar.android.marvelapp.tasks.format

import com.rockar.android.marvelapp.utils.FileUtils.findLocalChangesByModule
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.language.base.plugins.LifecycleBasePlugin
import org.gradle.process.JavaExecSpec

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
            classpath = project.configurations.getByName("ktlint")
            mainClass.set("com.pinterest.ktlint.Main")
            configureFilesToVerify()
        }
    }

    private fun JavaExecSpec.configureFilesToVerify() {
        val filesToCheck = if (project.hasProperty("localChanges")) {
            findLocalChangesByModule(project)
        } else arrayOf(
            "**/src/**/*.kt", // Include any kotlin file in the project
            "**.kts", // Include any kotlin script in the project
        )
        args(
            *filesToCheck,
            "!**/build/**", // exclude build folders
        )
    }
}
