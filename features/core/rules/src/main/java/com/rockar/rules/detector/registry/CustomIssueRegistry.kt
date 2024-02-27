package com.rockar.rules.detector.registry

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.rockar.rules.detector.ExcludedClassIfHiltModuleRule

class CustomIssueRegistry : IssueRegistry() {

    override val api: Int
        get() = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(ExcludedClassIfHiltModuleRule.ISSUE)

    override val vendor: Vendor
        get() = Vendor("HeroesApp")
}
