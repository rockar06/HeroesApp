package com.android.marvelapp.plugins.feature

import com.android.marvelapp.plugins.feature.techdebt.TechDebtExtension
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

open class FeaturePluginExtension @Inject constructor(
    objectFactory: ObjectFactory
) {

    private val techDebtObject = objectFactory.newInstance(TechDebtExtension::class.java)

    fun techDebt(action: TechDebtExtension.() -> Unit) {
        techDebtObject.action()
    }

    fun getTechDebtObject() = techDebtObject
}
