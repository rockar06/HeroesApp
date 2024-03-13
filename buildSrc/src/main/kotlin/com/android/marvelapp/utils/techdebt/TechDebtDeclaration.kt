package com.android.marvelapp.utils.techdebt

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

private const val MAX_TIME_TECH_DEBT_IN_DAYS = 90

object TechDebtDeclaration {

    fun techDebtList() = listOf(
        excludedClassIfHiltModuleTechDebt
    )

    private val excludedClassIfHiltModuleTechDebt = TechDebtItem(
        lintRuleName = "ExcludedClassIfHiltModuleRule",
        effectiveDate = LocalDate(2024, 3, 4)
    )

    data class TechDebtItem(
        val lintRuleName: String,
        val effectiveDate: LocalDate,
        val gracePeriod: LocalDate? = null
    )
}

fun TechDebtDeclaration.TechDebtItem.isExpired(): Boolean {
    val now = Clock.System.now()
    return gracePeriod?.let { (now - it.atStartOfDayIn(TimeZone.UTC)).inWholeDays > 0 } ?: {
        (now - effectiveDate.atStartOfDayIn(TimeZone.UTC)).inWholeDays > MAX_TIME_TECH_DEBT_IN_DAYS
    }()
}
