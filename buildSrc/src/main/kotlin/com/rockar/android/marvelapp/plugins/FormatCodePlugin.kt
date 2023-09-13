package com.rockar.android.marvelapp.plugins

import com.rockar.android.marvelapp.tasks.format.CheckCodeTask
import com.rockar.android.marvelapp.tasks.format.FormatCodeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.attributes.Bundling

class FormatCodePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        if (target.rootProject == target) {
            target.subprojects {
                addKtlintDependencyToProject(this)
                tasks.register("checkCodeFormat", CheckCodeTask::class.java)
                tasks.register("formatCode", FormatCodeTask::class.java)
            }
        }
    }

    private fun addKtlintDependencyToProject(target: Project) {
        target.configurations.create("ktlint") {
            dependencies.add(target.dependencies.create("com.pinterest:ktlint:0.48.2"))
            target.dependencies.constraints.create("com.pinterest:ktlint:0.48.2").apply {
                attributes {
                    attribute(
                        Bundling.BUNDLING_ATTRIBUTE,
                        target.objects.named(Bundling::class.java, Bundling.EXTERNAL),
                    )
                }
                dependencyConstraints.add(this)
            }
        }
    }
}
