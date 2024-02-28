package com.android.marvelapp.utils.coverage

import kotlinx.serialization.json.Json
import org.gradle.api.Project
import java.io.File

object CoverageUtils {

    private var coverageRules: CoverageRules? = null

    fun init(project: Project) {
        if (coverageRules != null) {
            return
        }

        val rootPath = project.rootDir.absolutePath
        val coverageRulesPath = "$rootPath/buildSrc/src/main/resources/coverage-rules.json"
        val coverageRulesJson = File(coverageRulesPath).readText(Charsets.UTF_8)
        coverageRules = Json.decodeFromString(coverageRulesJson)
    }

    fun getIgnoreModules(): List<String> {
        return coverageRules?.ignore.orEmpty()
    }

    fun getInstructionThreshold(): Double {
        return coverageRules?.instructionThreshold ?: 0.0
    }

    fun getBranchThreshold(): Double {
        return coverageRules?.branchThreshold ?: 0.0
    }
}
