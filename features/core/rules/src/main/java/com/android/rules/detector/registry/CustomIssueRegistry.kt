package com.android.rules.detector.registry

import com.android.rules.detector.ExcludedClassIfHiltModuleRule
import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class CustomIssueRegistry : IssueRegistry() {

    override val api: Int
        get() = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(ExcludedClassIfHiltModuleRule.ISSUE)

    override val vendor: Vendor
        get() = Vendor("HeroesApp")
}
