package com.rockar.android.marvelapp.tasks.format

import com.rockar.android.marvelapp.utils.FileUtils.findLocalChangesByModule
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.process.JavaExecSpec

abstract class FormatCodeTask : DefaultTask() {

    override fun getGroup(): String {
        return "formatting"
    }

    override fun getDescription(): String {
        return "Fix Kotlin code style deviations."
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
        println("Path for current project")
        val filesToCheck = if (project.hasProperty("localChanges")) {
            findLocalChangesByModule(project)
        } else arrayOf(
            "**/src/**/*.kt", // Include any kotlin file in the project
            "**.kts", // Include any kotlin script in the project
        )
        jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
        args(
            "-F", // Apply Autocorrect format if possible
            *filesToCheck,
            "!**/build/**", // exclude build folders
        )
    }
}
