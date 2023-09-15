package com.rockar.android.marvelapp.utils

import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.regex.Pattern

object FileUtils {

    private const val SPACE_DELIMITER = " "
    private const val EMPTY_STRING = ""
    private const val SRC_DIR_NAME = "src"
    private const val GIT_DIFF_COMMAND = "git diff --name-only --diff-filter=AMR HEAD"
    private const val SPLASH_PATH_DELIMITER = "/"
    private const val KOTLIN_FILES_PATTERN = "(\\.(kt|kts)\$)"
    private val EXCLUDED_PROJECTS = listOf(
        "",
        "buildSrc",
        "app-phone"
    )

    fun findLocalChangesByModule(project: Project): Array<String> {
        val changedFiles = findLocalChanges(project)

        val projectPath = project.projectDir.absolutePath.replace(
            "${project.rootDir.absolutePath}${File.separator}",
            ""
        ).replace("\\", "/") // for Windows OS

        return changedFiles.filter { it.startsWith("$projectPath/") }.toTypedArray()
    }

    fun findLocalChanges(project: Project): Array<String> {
        val systemOutStream = ByteArrayOutputStream()

        project.exec {
            commandLine = GIT_DIFF_COMMAND.split(SPACE_DELIMITER)
            standardOutput = systemOutStream
        }

        val result = systemOutStream.toString().trim().split('\n')
        systemOutStream.close()

        val kotlinFilesPattern = Pattern.compile(KOTLIN_FILES_PATTERN)
        val changedFiles = arrayListOf<String>()

        result.forEach {
            val matcher = kotlinFilesPattern.matcher(it)
            if (matcher.find()) {
                changedFiles.add("$it")
            }
        }

        return changedFiles.toTypedArray()
    }

    fun getModulesChanged(project: Project): Set<String> = findLocalChanges(project).map {
        val splitterString = it.split(SPLASH_PATH_DELIMITER)
        val indexSrc = splitterString.indexOf(SRC_DIR_NAME)
        splitterString.getOrElse(indexSrc - 1) { EMPTY_STRING }
    }.filterNot { EXCLUDED_PROJECTS.contains(it) }.toSet()
}
