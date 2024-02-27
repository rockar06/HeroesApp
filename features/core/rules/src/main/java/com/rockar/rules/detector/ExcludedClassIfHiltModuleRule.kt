package com.rockar.rules.detector

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UClass

class ExcludedClassIfHiltModuleRule : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes() = listOf(UClass::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitClass(node: UClass) {
                if (node.hasAnnotation(EXCLUDE_JACOCO_ANNOTATION) && !node.hasAnnotation(DAGGER_MODULE_ANNOTATION)) {
                    context.report(
                        issue = ISSUE,
                        location = context.getNameLocation(node),
                        message = "ExcludeClassFromJacocoGeneratedReport can be only used in Hilt modules.",
                    )
                }
            }
        }
    }

    companion object {

        private const val EXCLUDE_JACOCO_ANNOTATION = "com.rockar.utils.coverage.ExcludeClassFromJacocoGeneratedReport"
        private const val DAGGER_MODULE_ANNOTATION = "dagger.Module"
        private val IMPLEMENTATION = Implementation(
            ExcludedClassIfHiltModuleRule::class.java,
            Scope.JAVA_FILE_SCOPE,
        )

        val ISSUE = Issue.create(
            id = "ExcludedClassIfHiltModuleRule",
            briefDescription = "Usage of ExcludeClassFromJacocoGeneratedReport is only for Hilt modules",
            explanation = """
                Only abstract classes intended to be Hilt Modules can skip Jacoco Coverage report. Other classes not related with Hilt, should have their respective tests to pass coverage threshold.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 9,
            severity = Severity.ERROR,
            androidSpecific = true,
            implementation = IMPLEMENTATION,
        )
    }
}
