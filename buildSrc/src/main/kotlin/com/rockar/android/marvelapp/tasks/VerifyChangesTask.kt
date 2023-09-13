package com.rockar.android.marvelapp.tasks

import org.gradle.api.DefaultTask
import org.gradle.language.base.plugins.LifecycleBasePlugin

abstract class VerifyChangesTask : DefaultTask() {

    override fun getGroup(): String {
        return LifecycleBasePlugin.VERIFICATION_GROUP
    }

    override fun getDescription(): String {
        return "Check Kotlin code style and verify tests along with the code coverage"
    }
}
