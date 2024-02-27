package com.android.marvelapp.tasks.format

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

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
            workingDir = project.projectDir
            classpath = project.configurations.getByName("ktlint")
            mainClass.set("com.pinterest.ktlint.Main")
            jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
            args(
                "-F", // Apply Autocorrect format if possible
                "**/src/**/*.kt", // Include any kotlin file in the project
                "**.kts", // Include any kotlin script in the project
                "!**/build/**", // exclude build folders
            )
        }
    }
}
