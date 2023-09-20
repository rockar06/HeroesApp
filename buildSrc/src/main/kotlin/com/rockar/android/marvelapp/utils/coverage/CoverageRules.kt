package com.rockar.android.marvelapp.utils.coverage

import kotlinx.serialization.Serializable

@Serializable
data class CoverageRules(
    val branchThreshold: Double,
    val instructionThreshold: Double,
    val ignore: List<String>
)
